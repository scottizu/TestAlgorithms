package algorithm;

public class RotateNbyNImage {
	public static void main(String[] args) {
		System.out.println("0");
		int N = 4;
		int[][] imageArray = new int[N][N];
		imageArray[0] = new int[]{1, 2, 3, 4};
		imageArray[1] = new int[]{5, 6, 7, 8};
		imageArray[2] = new int[]{9, 10, 11, 12};
		imageArray[3] = new int[]{13, 14, 15, 16};
		
		printImage(imageArray);
		
		System.out.println("90");
		rotate90Degrees(imageArray);
		// (0,0) -> (0,3) // Upper left quadrant, (x,y) -> (y,N-x)
		// (0,3) -> (3,3) // Upper right quadrant, (x,y) -> (y,N-x)
		// (3,3) -> (3,0) // Lower right quadrant, (x,y) -> (y,N-x)
		// (3,0) -> (0,0) // Lower left quadrant, (x,y) -> (y,N-x)

		printImage(imageArray);

		System.out.println("180");
		rotate90Degrees(imageArray);
		printImage(imageArray);
		System.out.println("270");
		rotate90Degrees(imageArray);
		printImage(imageArray);
		System.out.println("360");
		rotate90Degrees(imageArray);
		printImage(imageArray);

		System.out.println("0");
		int N2 = 3;
		int[][] imageArray2 = new int[N2][N2];
		imageArray2[0] = new int[]{1, 2, 3};
		imageArray2[1] = new int[]{5, 6, 7};
		imageArray2[2] = new int[]{9, 10, 11};
		printImage(imageArray2);
		System.out.println("90");
		rotate90Degrees(imageArray2);
		printImage(imageArray2);
		}

	private static void rotate90Degrees(int[][] imageArray) {
		int N = imageArray[0].length;
		int maxX = (int) Math.floor((double)N/2) - 1;
		int maxY = (int) Math.ceil((double)N/2) - 1;
//		System.out.println("maxX:"+maxX+" maxY:"+maxY);
		
		for(int x1=0; x1<=maxX; x1++) {
			for(int y1=0; y1<=maxY; y1++) {
				int x = x1;
				int y = y1;
				int pixelValue = imageArray[x][y];
//				System.out.println("rotatiion for ("+x+","+y+")");
				for(int i=0; i<4; i++) {
					int newx = y;
					int newy = N-1-x;
					int newPixelValue = imageArray[newx][newy];

//					System.out.println("("+x+","+y+")->("+newx+","+newy+")");
					
					imageArray[newx][newy] = pixelValue;
					x = newx;
					y = newy;
					pixelValue = newPixelValue;
				}
			}
		}
	}

	private static void printImage(int[][] imageArray) {
		for(int i=0; i<imageArray.length; i++) {
			int[] row = imageArray[i];
			StringBuilder rowBuilder = new StringBuilder();
			String del = "";
			for(int j=0; j<row.length; j++) {
				int pixel = row[j];
				rowBuilder.append(del + pixel);
				del = ",";
			}
			System.out.println(rowBuilder.toString());
		}
	}
}
