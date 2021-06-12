package com.majm.aop.asm;


import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ASM demo </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-11 13:18
 * @since
 */
public class TestAsm {

    public static void main(String[] args) {
        //ClassReader用于读取原有字节码，ClassWriter用于写入字节码，
        ClassWriter classWriter = new ClassWriter(0);
        // 通过 visit确定类的同步信息, java 版本号 类的修饰符 类全限定名
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Duck", null, "java/lang/Object", null);
        // 构造函数
        MethodVisitor mv = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();

        // 定义 code()
        MethodVisitor codeMethod = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "code", "()V", null, null);
        codeMethod.visitCode();
        codeMethod.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintSteam;");
        codeMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        codeMethod.visitInsn(Opcodes.RETURN);
        codeMethod.visitMaxs(2, 2);
        codeMethod.visitEnd();

        classWriter.visitEnd();
        // 使classWriter类已经完成
        // 将classWriter转换成字节数组写到文件里面去
        byte[] data = classWriter.toByteArray();
        File file = new File("./Duck.class");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
