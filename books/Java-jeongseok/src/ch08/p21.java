package ch08;

public class p21 {
    public static void main(String[] args) {
        try{
            startInstall();
            copyFiles();
        } catch (SpaceException e){
            System.out.println("e.getMessage() = " + e.getMessage());
            e.printStackTrace();
            System.out.println("공간 확보 후에 다시 시도");
        } catch (MemoryException e){
            System.out.println("e.getMessage() = " + e.getMessage());
            e.printStackTrace();
            System.gc();
            System.out.println("다시 설치 시도하세요.");
        } finally {
            deleteTempFiles();
        }
    }
    static void startInstall() throws SpaceException, MemoryException {
        if(!enoughSpace()) {
            throw new SpaceException("설치할 공간이 부족합니다.");
        }
        if (!enoughMemory()){
            throw new MemoryException("메모리가 부족합니다.");
        }
    }
    static void copyFiles() { /* 복사 작업 수행 */ }
    static void deleteTempFiles() { /* 임시 파일 삭제 작업 수행 */ }
    static boolean enoughSpace() {
        return false;
    }
    static boolean enoughMemory() {
        return true;
    }
}
class SpaceException extends Exception {
    SpaceException(String msg) {super(msg);}
}
class MemoryException extends Exception {
    MemoryException(String msg) {super(msg);}
}