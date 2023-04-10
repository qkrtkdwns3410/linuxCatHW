package linux.java.SequenceStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.Arrays;
import java.util.Vector;

/**
 * packageName    : linux.java.SequenceStream
 * fileName       : SequenceInputStreamEx
 * author         : ipeac
 * date           : 2023-04-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-09        ipeac       최초 생성
 */
public class SequenceInputStreamEx {
    public static void main(String[] args) throws IOException {
        byte[] arr1 = {0, 1, 2};
        byte[] arr2 = {3, 4, 5};
        byte[] arr3 = {6, 7, 8};
        //SequenceInputStream 으로 읽어들인 데이터가 저장될 물리적 데이터
        byte[] outSrc = null;
        
        //.. vector 사용하는거 처음봅니다. (사장된걸로알고있는데..)
        Vector v = new Vector();
        v.add(new ByteArrayInputStream(arr1));
        v.add(new ByteArrayInputStream(arr2));
        v.add(new ByteArrayInputStream(arr3));
        
        //시퀀스 인풋 스트림을 열어줍니다.
        SequenceInputStream input = new SequenceInputStream(v.elements());
        //물리적 데이터로 쓰기위하여 byteArrayOutputStream 을 열어줍니다.
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //처리단위겸 종료 체크하기 위한 데이터입니다.
        int data = 0;
        //-1 로 스트림의 종료를 호출할때까지 data에 1바이트씩 물리적 데이터를 읽습니다.
        while ((data = input.read()) != -1) {
            //물리적데이터가 outputStream에 쓰여집니다 (1바이트씩)
            outputStream.write(data);
        }
        //내부에 쌓아둔 byte 배열을 반환합니다.
        outSrc = outputStream.toByteArray();
        System.out.println("arr1 = " + Arrays.toString(arr1));
        System.out.println("arr2 = " + Arrays.toString(arr2));
        System.out.println("arr3 = " + Arrays.toString(arr3));
        System.out.println("outSrc = " + Arrays.toString(outSrc));
    }
}
