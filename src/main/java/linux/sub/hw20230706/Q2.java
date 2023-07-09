package linux.sub.hw20230706;

import java.text.MessageFormat;
import java.util.Arrays;

/**
 * packageName    : linux.sub.hw20230706
 * fileName       : Q2
 * author         : ipeac
 * date           : 2023-07-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-07-09        ipeac       최초 생성
 */
public class Q2 {
    public static void main(String[] args) {
        Q2 q2 = new Q2();
        System.out.println(q2.solution(
                new int[][]{{10, 0, 30, 5}, {0, 30, 20, 50}, {30, 30, 40, 40}},
                new int[][]{{15, 15, 25, 25}, {40, 10, 50, 20}},
                new int[]{10, 10, 30, 30}
        ));
    }
    
    public boolean solution(int[][] lands, int[][] wells, int[] point) {
        Parcel[] purchasedLands = Parcel.from(lands);
        Parcel[] parcelWells = Parcel.from(wells);
        Parcel wantedPoint = Parcel.from(point);
        for (Parcel purchasedLand : purchasedLands) {
            boolean isPurchasedLandOverlap = wantedPoint.isOverlap(purchasedLand);
            if (isPurchasedLandOverlap) {
                return false;
            }
        }
        for (Parcel parcelWell : parcelWells) {
            boolean isParcelWellOverlap = wantedPoint.isOverlap(parcelWell);
            if (isParcelWellOverlap) {
                return true;
            }
        }
        return false;
    }
    
    // 부지
    public static class Parcel {
        private static final IllegalArgumentException NULL_ARGUMENTATION_ERROR = new IllegalArgumentException("NULL 값은 허용되지 않습니다");
        private final Point bottomLeft;
        private final Point topRight;
        
        public Parcel(Point bottomLeft, Point topRight) {
            if (bottomLeft == null || topRight == null) {
                throw NULL_ARGUMENTATION_ERROR;
            }
            this.bottomLeft = bottomLeft;
            this.topRight = topRight;
        }
        
        public boolean isOverlap(Parcel other) {
            if (other == null) {
                throw NULL_ARGUMENTATION_ERROR;
            }
            boolean isXinMyParcel = ((this.bottomLeft.x < other.bottomLeft.x && other.bottomLeft.x < this.topRight.x) ||
                                             (this.bottomLeft.x < other.topRight.x && other.topRight.x < this.topRight.x));
            boolean isYinMyParcel = ((this.bottomLeft.y < other.bottomLeft.y && other.bottomLeft.y < this.topRight.y) ||
                                             (this.bottomLeft.y < other.topRight.y && other.topRight.y < this.topRight.y));
            if (isXinMyParcel && isYinMyParcel) {
                return true;
            }
            return false;
        }
        
        
        public static Parcel from(int[] parcelInfo) {
            if (parcelInfo == null) {
                throw NULL_ARGUMENTATION_ERROR;
            }
            if (parcelInfo.length != 4) {
                String errorMessage = MessageFormat.format("parcelInfo => {0} 의 길이가 4가 아닙니다", Arrays.toString(parcelInfo));
                throw new IllegalArgumentException(errorMessage);
            }
            return new Parcel(new Point(parcelInfo[0], parcelInfo[1]), new Point(parcelInfo[2], parcelInfo[3]));
        }
        
        public static Parcel[] from(int[][] parcelInfos) {
            if (parcelInfos == null) {
                throw NULL_ARGUMENTATION_ERROR;
            }
            return getParcels(parcelInfos);
        }
        
        private static Parcel[] getParcels(int[][] parcelInfos) {
            Parcel[] parcels = new Parcel[parcelInfos.length];
            int index = 0;
            for (int[] parcelInfo : parcelInfos) {
                parcels[index++] = from(parcelInfo);
            }
            return parcels;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            
            Parcel parcel = (Parcel) o;
            
            if (!bottomLeft.equals(parcel.bottomLeft)) return false;
            return topRight.equals(parcel.topRight);
        }
        
        @Override
        public int hashCode() {
            int result = bottomLeft.hashCode();
            result = 31 * result + topRight.hashCode();
            return result;
        }
        
        @Override
        public String toString() {
            return "Parcel{" +
                           "bottomLeft=" + bottomLeft +
                           ", topRight=" + topRight +
                           '}';
        }
    }
    
    public static class Point {
        private final int x;
        private final int y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
    }
}
