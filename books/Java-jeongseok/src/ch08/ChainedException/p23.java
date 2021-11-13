package ch08.ChainedException;

public class p23 {
    public static void main(String[] args) {
        try{
            install();
        } catch (InstallException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    static void install() throws InstallException {
        try{
            startInstall();
            copyFiles();
        } catch (SpaceException e) {
            InstallException ie = new InstallException("Install Exception");
            ie.initCause(e);
            throw ie;
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        finally {
            deleteTempFiles();
        }
    }
    static void startInstall() throws SpaceException {
        if(!enoughSpace()) {
            throw new SpaceException("Space Not Enough");
        }
        if(!enoughMemory()) {
            throw new RuntimeException(new MemoryException("Memory Not Enough"));
        }
    }
    static void copyFiles() {}
    static void deleteTempFiles() {}
    static boolean enoughSpace() { return true; }
    static boolean enoughMemory() { return false; }
}

class InstallException extends Exception {
    InstallException(String msg) {super(msg);}
}
class SpaceException extends Exception {
    SpaceException(String msg) {super(msg);}
}
class MemoryException extends Exception {
    MemoryException(String msg) {super(msg);}
}