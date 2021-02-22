package ch08;

public class p20 {
    public static void main(String[] args) {
        // CloseException은 무조건 발생함
        try(CloseableResource cr = new CloseableResource()){
            cr.exceptionWork(false); // 예외 발생시키지 않음
        } catch (WorkException e){
            e.printStackTrace();
        } catch (CloseException e){
            e.printStackTrace();
        }
        System.out.println("----------");
        try(CloseableResource cr = new CloseableResource()){
            cr.exceptionWork(true); // 예외 발생시킴
        } catch (WorkException e){
            e.printStackTrace(); // 기본 WorkException으로 발생하고 Suppressed Exception으로 CloseException발생
        } catch (CloseException e){
            e.printStackTrace();
        }
    }
}
class CloseableResource implements AutoCloseable {
    public void exceptionWork(boolean exception) throws WorkException {
        System.out.println("ClosableResource.exceptionWork!!");
        if(exception) {
            throw new WorkException("WorkdException!!");
        }
    }
    @Override
    public void close() throws CloseException {
        System.out.println("ClosableResource.close!!");
        throw new CloseException("CloseException!!");
    }
}
class WorkException extends Exception {
    WorkException(String msg) {super(msg);}
}
class CloseException extends Exception {
    CloseException(String msg) {super(msg);}
}