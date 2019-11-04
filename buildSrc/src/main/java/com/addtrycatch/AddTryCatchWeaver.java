package com.addtrycatch;

import com.quinn.hunter.transform.asm.BaseWeaver;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;


class AddTryCatchWeaver extends BaseWeaver {
//    private String currentClassName;


    @Override
    public boolean isWeavableClass(String fullQualifiedClassName) {
//        currentClassName = fullQualifiedClassName;
        return Config.getInstance().extension.hookPoint.containsKey(fullQualifiedClassName.replace(".class", ""));
    }


    @Override
    protected ClassVisitor wrapClassWriter(ClassWriter classWriter) {
        return new AddTryCatchClassAdapter(classWriter);
    }
//    @Override
//    public byte[] weaveSingleClassToByteArray(InputStream inputStream) throws IOException {
//        byte[] bytes = super.weaveSingleClassToByteArray(inputStream);
//        if(currentClassName==null||"".equals(currentClassName)){
//            currentClassName = "error.class";
//        }
//        OutputStream out = new FileOutputStream("./app/build/intermediates/transforms/"+currentClassName);
//        InputStream is = new ByteArrayInputStream(bytes);
//        byte[] buff = new byte[1024];
//        int len = 0;
//        while((len=is.read(buff))!=-1){
//            out.write(buff, 0, len);
//        }
//        is.close();
//        out.close();
//        return bytes;
//    }


}
