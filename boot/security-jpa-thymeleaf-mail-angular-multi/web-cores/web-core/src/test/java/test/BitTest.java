package test;

public class BitTest {
//    public int serialNumber = 176;
    public int serialNumber = 1;

    public static void main(String[] args) {
        new BitTest().run();

        System.out.println("=============================");
        new BitTest().run2();
//        serialNumber = 0 << 14;
//        System.out.println(Short.SIZE);
//        System.out.println(String.format("%"+Short.SIZE+"s", Long.toBinaryString(serialNumber)).replace(" ", "0"));
//        serialNumber = 0 << 14;
//        System.out.println(String.format("%"+Short.SIZE+"s", Long.toBinaryString(serialNumber)).replace(" ", "0"));
//
//        int i = 0;
//        System.out.println(String.format("%"+Integer.SIZE+"s", Long.toBinaryString(i)).replace(" ", "0"));
//        i = 0 << 14;
//        System.out.println(String.format("%"+Integer.SIZE+"s", Long.toBinaryString(i)).replace(" ", "0"));

        ////////////

    }

    public void run() {
        System.out.println("init--> " + String.format("%"+Integer.SIZE+"s", Long.toBinaryString(this.serialNumber)).replace(" ", "0"));
        short serialNumber = 0;

        serialNumber = 0 << 14;
        System.out.println(String.format("%"+Short.SIZE+"s", Long.toBinaryString(serialNumber)).replace(" ", "0"));

//        int temp = this.serialNumber & 0x000003f0;
//        System.out.println(String.format("%"+Integer.SIZE+"s", Long.toBinaryString(temp)).replace(" ", "0"));

        serialNumber |= (this.serialNumber & 0x000003f0) << 4;
        System.out.println("내거 그릇으로 담기-> "+String.format("%"+Short.SIZE+"s", Long.toBinaryString(serialNumber)).replace(" ", "0"));

        serialNumber |= (this.serialNumber & 0x0000000f) << 4;
        System.out.println(String.format("%"+Short.SIZE+"s", Long.toBinaryString(serialNumber)).replace(" ", "0"));

        serialNumber |= 0;
        System.out.println(String.format("%"+Short.SIZE+"s", Long.toBinaryString(serialNumber)).replace(" ", "0"));

        System.out.println(String.format("%02X", serialNumber));


        //
//        String foo = "I am a string";
//        byte[] bytes = foo.getBytes();
//        System.out.println( Hex.encodeHexString( bytes ) );
    }

    public void run2() {
        System.out.println("init--> " + String.format("%"+Integer.SIZE+"s", Long.toBinaryString(this.serialNumber)).replace(" ", "0"));
        short serialNumber = 0;

        serialNumber = 0 << 14;
        System.out.println(String.format("%"+Short.SIZE+"s", Long.toBinaryString(serialNumber)).replace(" ", "0"));

//        int temp = this.serialNumber & 0x000003f0;
//        System.out.println(String.format("%"+Integer.SIZE+"s", Long.toBinaryString(temp)).replace(" ", "0"));

        serialNumber |= (this.serialNumber & 0x000003ff) << 4;

        serialNumber |= 0;
        System.out.println(String.format("%"+Short.SIZE+"s", Long.toBinaryString(serialNumber)).replace(" ", "0"));

        System.out.println(String.format("%02X", serialNumber));


        //
//        String foo = "I am a string";
//        byte[] bytes = foo.getBytes();
//        System.out.println( Hex.encodeHexString( bytes ) );
    }
}
