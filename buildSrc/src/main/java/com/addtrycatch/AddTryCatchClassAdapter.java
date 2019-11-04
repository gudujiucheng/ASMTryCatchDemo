package com.addtrycatch;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.List;
import java.util.Map;


class AddTryCatchClassAdapter extends ClassVisitor {
    private String mClassName;
    private Map<String,Object> mMethodNames;

    public AddTryCatchClassAdapter(ClassWriter classWriter) {
        super(Opcodes.ASM5, classWriter);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        mClassName = name.replace("/", ".");
        mMethodNames = Config.getInstance().extension.hookPoint.get(mClassName);
        super.visit(version, access, name, signature, superName, interfaces);
        System.out.println("add try catch class :" + mClassName);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if (mMethodNames.containsKey(name)) {
            return new AddTryCatchAdviceAdapter(mMethodNames.get(name),Opcodes.ASM5,
                    super.visitMethod(access, name, desc, signature, exceptions), access, name, desc);
        }
        return super.visitMethod(access, name, desc, signature, exceptions);
    }
}
