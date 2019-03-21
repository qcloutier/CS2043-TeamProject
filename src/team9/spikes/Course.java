package team9.spikes;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Course{
		private String courseId;
		private String campusSection;
		private String letterGrade;
		private double creditHours;
		
		public Course(String campusSection, String courseId, String letterGrade, double creditHours){
			
			this.courseId = courseId;
			this.campusSection = campusSection;
			this.letterGrade = letterGrade;
			this.creditHours = creditHours;
		
		}
		
		public static void main(String[] args) throws IOException {
			ArrayList<Course> Courses = new ArrayList<Course>();
			FileReader fr=new FileReader("demo/Sample Transcript.txt");
			BufferedReader br=new BufferedReader(fr);
			String line="";
			String[] courseInfo=null;
			int num=0;
		
			while ((line=br.readLine())!=null) {	
				courseInfo=line.split("\\s\\s+");
				if(courseInfo.length==7) {	
					String section=courseInfo[2];
					String name=courseInfo[1];
					String grade=courseInfo[4];
					double creditHours=Double.valueOf(courseInfo[5]);
					System.out.println(section+"|"+name+"|"+grade+"|"+creditHours);
					
					Courses.add(new Course(section, name, grade, creditHours));
				}

			num++;
			}
			br.close();
			fr.close();

			for(Course c:Courses) {
				System.out.println(c.courseId + " and " + c.campusSection + " and "  + c.letterGrade + " and " + c.creditHours);
			}
		
		}
}
