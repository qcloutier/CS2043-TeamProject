package team9.spikes;

import java.io.*;

public class Course{
		private String courseId;
		private String campusSection;
		private String courseName;
		private String letterGrade;
		private double pointGrade;
		private String yearSemester;
		
		public Course(String courseId, String campusSection, String courseName, String letterGrade, double pointGrade, String yearSemester){
			
			this.courseId = courseId;
			this.campusSection = campusSection;
			this.courseName = courseName;
			this.letterGrade = letterGrade;
			this.pointGrade = pointGrade;
			this.yearSemester = yearSemester;
		
		}
		
		public static void main(String[] args) throws IOException {
			Course[] e = new Course[6];
			FileReader fr=new FileReader("YourDoc.txt");
			BufferedReader br=new BufferedReader(fr);
			String line="";
			String[] arrs=null;
			int num=0;
		
			while ((line=br.readLine())!=null) {
				arrs=line.split("\\s+");

			e[num] = new Course(arrs[1], arrs[2], arrs[3], arrs[4], Double.valueOf(arrs[5]), arrs[6]);

			num++;
			}
			br.close();
			fr.close();

			for(int i=0 ; i< e.length; i++) {
				System.out.println(e[i].courseId + " and " + e[i].campusSection + " and " + e[i].courseName + " and " + e[i].letterGrade + " and " + e[i].pointGrade + " and " + e[i].yearSemester);
			}
		
		}
}
