package com.addtrycatch;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

import java.util.Map;


public class AddTryCatchAdviceAdapter extends AdviceAdapter {

    private Label l1;
    private Label l2;
    private String exceptionHandleClass;
    private String exceptionHandleMethod;
    private Object returnObject;

    protected AddTryCatchAdviceAdapter(Object returnObject, int api, MethodVisitor mv, int access, String name, String desc) {
        super(api, mv, access, name, desc);
        this.returnObject = returnObject;
        Map<String, String> exceptionHandler = Config.getInstance().extension.exceptionHandler;
        if (exceptionHandler != null && !exceptionHandler.isEmpty()) {
            exceptionHandler.entrySet().forEach(entry -> {
                exceptionHandleClass = entry.getKey().replace(".", "/");
                exceptionHandleMethod = entry.getValue();
            });
        }
    }

    @Override
    protected void onMethodEnter() {
        super.onMethodEnter();
        Label l0 = new Label();
        l1 = new Label();
        l2 = new Label();
        mv.visitTryCatchBlock(l0, l1, l2, "java/lang/Exception");
        mv.visitLabel(l0);
    }

    @Override
    protected void onMethodExit(int i) {
        super.onMethodExit(i);
        mv.visitLabel(l1);
        Label l3 = new Label();
        mv.visitJumpInsn(GOTO, l3);
        mv.visitLabel(l2);
        mv.visitVarInsn(ASTORE, 0);
        Label l4 = new Label();
        mv.visitLabel(l4);
        if (exceptionHandleClass != null && exceptionHandleMethod != null) {
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESTATIC, exceptionHandleClass,
                    exceptionHandleMethod, "(Ljava/lang/Exception;)V", false);
            Label l5 = new Label();
            mv.visitLabel(l5);
        }
        if (returnObject instanceof Boolean) {
            mv.visitInsn(((Boolean) returnObject) ? ICONST_1 : ICONST_0);
            mv.visitInsn(IRETURN);
        }

        mv.visitLabel(l3);
    }
}
