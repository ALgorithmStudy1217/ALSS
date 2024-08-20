package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*1. �׽�Ʈ ���̽� �Է¹ޱ�
 *2. ��ǰ ����Ƚ�� 1�� �ø��鼭 ��ǰ ������ �� ���� ã��
 *3. A ����/B����/���� X ���� ��� ȣ��
 *4. ��������
 *	4-1. ���� �ϼ��ϸ� ���ɰ˻� ����ߴ��� �˻��ϱ� -> checkPerformance �Լ�
 *	4-2. ��� ���� Ȯ���ߴٸ� ����
 *5. ���� �˻� ����ߴٸ� ������ ��� ���� �ø��� �ʰ� ����
 * */

public class Swea_2112 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int height, width, passScore, selectNum;
	static int[][] film;
	static int[][] copy;
	static boolean isSuccess;	//���� ����
	
	static boolean checkPerformance() {
		for(int col=0; col<width; col++) {
			int same=1;
			for(int row=1; row<height; row++) {
				if(copy[row][col]==copy[row-1][col]) {
					same+=1;
				}else {
					same=1;
				}
				
				//1���� ������ ���ɰ˻縦 ����ߴٸ� break �ϰ� ������ �˻��ϱ�
				if(same==passScore) {
					break;
				}
			}
			//1���� ���̶� ���ɰ˻縦 ������� ���ߴٸ� �ٷ� false ����
			if(same<passScore) {
				return false;
			}
		}
		//��� ���� ���ɰ˻縦 ����ߴٸ� true ����
		return true;
	}
	
	static void combination(int selectIdx, int elementIdx) {
		//4. ��������
		//4-1. ���� �ϼ��ϸ� ���ɰ˻� ����ߴ��� �˻��ϱ� -> checkPerformance �Լ�
		if(selectIdx==selectNum) {
			if(checkPerformance()){
				isSuccess=true;
			}
			return;
		}
		
		//4-2. ��� ���� Ȯ���ߴٸ� ����
		if(elementIdx==height) {
			return;
		}
		
		
		//3. A ����/B����/���� X ���� ��� ȣ��
		//element�� ��-A ����
		for(int idx=0; idx<width; idx++) {
			copy[elementIdx][idx]=0;
		}
		combination(selectIdx+1,elementIdx+1);
		for(int idx=0; idx<width; idx++) {	//��Ʈ��ŷ
			copy[elementIdx][idx]=film[elementIdx][idx];
		}
		
		//element�� ��-B����
		for(int idx=0; idx<width; idx++) {
			copy[elementIdx][idx]=1;
		}
		combination(selectIdx+1,elementIdx+1);
		for(int idx=0; idx<width; idx++) {	//��Ʈ��ŷ
			copy[elementIdx][idx]=film[elementIdx][idx];
		}
			
		//element�� ���� ����
		combination(selectIdx,elementIdx+1);
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		int T=Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			//1. �׽�Ʈ ���̽� �Է¹ޱ�
			st=new StringTokenizer(br.readLine());
			height=Integer.parseInt(st.nextToken());
			width=Integer.parseInt(st.nextToken());
			passScore=Integer.parseInt(st.nextToken());
			
			isSuccess=false;
			
			film=new int [height][width];
			copy=new int [height][width];
			
			for(int row=0; row<height; row++) {
				st=new StringTokenizer(br.readLine());
				for(int col=0; col<width; col++) {
					int tempNum=Integer.parseInt(st.nextToken());
					film[row][col]=tempNum;	//���� �迭
					copy[row][col]=tempNum;	//�����ų �迭
				}
			}
			
			//2. ��ǰ ����Ƚ�� 1�� �ø��鼭 ��ǰ ������ �� ���� ã��
			for(int idx=0; idx<height; idx++) {
				selectNum=idx;	//���� element ��
				combination(0,0);
				//5. ���� �˻� ����ߴٸ� ������ ��� ���� �ø��� �ʰ� ����
				if(isSuccess) {
					break;
				}
			}
			sb.append("#"+test_case+" "+selectNum+"\n");
		}
		System.out.println(sb);
	}

}
