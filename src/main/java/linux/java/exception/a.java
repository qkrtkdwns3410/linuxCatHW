package linux.java.exception;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * packageName    : linux.java.exception
 * fileName       : a
 * author         : ipeac
 * date           : 2023-05-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-03        ipeac       최초 생성
 */
public class a {
    public static void main(String[] args) throws IOException {
        try (CloseableResource cr = new CloseableResource()) {
            cr.excpetionWork(false);
        } catch (WorkException | CloseException e) {
            e.printStackTrace();
        }
        System.out.println();
        
        try (CloseableResource cr = new CloseableResource()) {
            cr.excpetionWork(true); // 예외 발생
        } catch (WorkException | CloseException exception) {
            exception.printStackTrace();
        }
    }
}

class CloseableResource implements AutoCloseable {
    public void excpetionWork(boolean exception) throws WorkException {
        System.out.println("exceptionWork(" + exception + ")가 호출됨");
        if (exception) {
            throw new WorkException("WorkException발생!!");
        }
    }
    
    @Override
    public void close() throws CloseException {
        System.out.println("close() 가 호출됨");
        throw new CloseException("Closeexception 발생");
    }
}
