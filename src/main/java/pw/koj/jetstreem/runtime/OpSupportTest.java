package pw.koj.jetstreem.runtime;

import java.io.*;
import java.util.*;
import java.lang.invoke.*;
import java.lang.invoke.MethodHandles.Lookup;
import org.objectweb.asm.*;

import pw.koj.jetstreem.runtime.type.*;

import static java.lang.invoke.MethodType.methodType;
import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;
import static org.objectweb.asm.ClassWriter.COMPUTE_MAXS;

public class OpSupportTest {
    public static void main(String[] args) throws Throwable {
        FileOutputStream fos = new FileOutputStream("StrmTop.class", false);
        byte[] b = dump01();
        fos.write(b);
        /*
        fos = new FileOutputStream("StrmTop$ns1.class", false);
        b = dump05ns1();
        fos.write(b);
        fos = new FileOutputStream("StrmTop$ns1$ns2.class", false);
        b = dump05ns2();
        fos.write(b);
        */
    }

    public static byte[] dump01() throws Exception {
        /* #streem#
         * 3 + 4
         *
         * #java#
         * public class StrmTop {
         *     public static void main(String[] args) {
         *         opPlus(3, 4); //INDY
         *     }
         * }
         */

        String className = "StrmTop";

        ClassWriter cw = new ClassWriter(COMPUTE_FRAMES | COMPUTE_MAXS);
        MethodVisitor mv;
        FieldVisitor fv;

        MethodType bsmMethodType = methodType(
            CallSite.class,
            Lookup.class,
            String.class,
            MethodType.class);

        Handle bsmMethodHandle = new Handle(
            H_INVOKESTATIC,
            OpSupport.class.getName().replace('.', '/'),
            "bootstrap",
            bsmMethodType.toMethodDescriptorString());

        cw.visit(52, ACC_SUPER, className, null, "java/lang/Object", null);

        fv = cw.visitField(ACC_PRIVATE + ACC_STATIC, "swps", "[Ljava/lang/invoke/SwitchPoint;", null, null);
        fv.visitEnd();

        mv = cw.visitMethod(0, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        mv.visitCode();
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitFieldInsn(GETSTATIC, "StrmTop", "swps", "[Ljava/lang/invoke/SwitchPoint;");
        mv.visitTypeInsn(NEW, "pw/koj/jetstreem/runtime/type/StrmInteger");
        mv.visitInsn(DUP);
        mv.visitLdcInsn(new Long(3));
        mv.visitMethodInsn(INVOKESPECIAL, "pw/koj/jetstreem/runtime/type/StrmInteger", "<init>", "(J)V", false);
        //mv.visitMethodInsn(INVOKESTATIC, "pw/koj/jetstreem/runtime/type/StrmInteger", "generate", "(J)Ljava/lang/Object;", false);
        mv.visitTypeInsn(NEW, "pw/koj/jetstreem/runtime/type/StrmInteger");
        mv.visitInsn(DUP);
        mv.visitLdcInsn(new Long(4));
        mv.visitMethodInsn(INVOKESPECIAL, "pw/koj/jetstreem/runtime/type/StrmInteger", "<init>", "(J)V", false);
        //mv.visitMethodInsn(INVOKESTATIC, "pw/koj/jetstreem/runtime/type/StrmInteger", "generate", "(J)Ljava/lang/Object;", false);
        mv.visitInvokeDynamicInsn("opPlus",
                "([Ljava/lang/invoke/SwitchPoint;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",
                new Handle(H_INVOKESTATIC, "pw/koj/jetstreem/runtime/OpSupport", "bootstrap", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/Integer;)Ljava/lang/invoke/CallSite;", false), new Integer(0));
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/Object;)V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        mv = cw.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
        mv.visitCode();
        mv.visitInsn(ICONST_5);
        mv.visitTypeInsn(ANEWARRAY, "java/lang/invoke/SwitchPoint");
        mv.visitFieldInsn(PUTSTATIC, "StrmTop", "swps", "[Ljava/lang/invoke/SwitchPoint;");
        mv.visitInsn(RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        cw.visitEnd();

        return cw.toByteArray();
    }
}


