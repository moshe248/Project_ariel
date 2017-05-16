import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

//// צריך להוסיף פונ שמחשבת נומינלית כמה אפשרויות תאוריטית יש 
//צריך להוסיף פונ שבודקת כמה אפשריות בתכלס חישבתנו ועל כמה דילגנו כי לא היה צריך לחשב
// צריך לבדוק כל פונ בכל מחלקה ולכתוב מה מותר להחזיר ומה אסור כנ"ל לגבי לקבל והדפסת שגיאה על הפונ במידה וקיבלנו ערך אסור
public class Department {
	Model[] models;
	Teacher[] teachers;
	//final int max_num_of_results = 500;
	boolean schedual_Department = false;
	BufferedWriter bw = null;
	FileWriter fw = null;
	//int save_dayIndex = 0;
	//int save_hourIndex = 0;

	public Department(Model[] models, Teacher[] teachers){
		this.models = models;
		this.teachers = teachers;

		try
		{
			fw = new FileWriter("my_result.txt");
			bw = new BufferedWriter(fw);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
	}

	/*public void run_app(){

		for (int i = 0; i < models.length; i++) {
			models[i].build_model();
		}
	}*/

	//צריך לעטוף בתנאי שאם לא מצליח קליר מודל ושבת נקסט וכו
	/*	public void build_department(){
		boolean possibole = true;;
		for (int i = 0; i < models.length && possibole == true; i++) {
			possibole= models[i].schedule_Model();
		}
		if(possibole == true) System.out.println( "concradulation ");
		else System.out.println(" you faild");
	}*/
	public boolean build_empty_department(){
		return build_department_start_at_model(0);
	}	
	public boolean build_department_start_at_model(int start_sched_from_model_index){
		schedual_Department = false;
		boolean build_model ;
		int i = start_sched_from_model_index;

		while ( i > -1 && i < models.length   )		
		{
			build_model = models[i].schedule_Model();
			if( build_model == true) i++;
			else 
			{
				models[i].clear_model();
				i--;		
			}
		}
		if( i == -1 )
		{
			schedual_Department = false;
			//System.out.println("you did not succeed building all models of this department ");			
			return false;
		}
		if( i == models.length  ){
			schedual_Department = true;
			/*	System.out.println("you succeed building all models of this department! concradulation! ");
			System.out.println("*******************************************************************************************");
			print_Department();*/
		}
		return true;
	}	
	public boolean build_next_department(){

		if( schedual_Department ) return build_department_start_at_model( models.length - 1 );
		else 
		{
			int i = find_last_build_model_and_clean_irrelevats();
			if(i == -1 ){
				System.out.println( "no more optonal Department");	
				return false;			
			}
			for (int j =  models.length-1; j > i ; j--) {
				models[j].clear_model();
			}

			return build_department_start_at_model(i);
		}
	}	
	public int find_last_build_model_and_clean_irrelevats(){
		int last = -1; 	
		int i;
		for ( i = 0; i < models.length && last == -1; i++) {

			if( ! models[i].Is_schedule) last = i - 1  ;
		}	
		if( last != -1){
			for ( i = models.length - 1; i > last; i--) 
			{
				models[i].clear_model();
			}
		}
		return last;
	}
	public void build_all_departments(){

		try
		{
			fw = new FileWriter("my_result.txt",true);
			bw = new BufferedWriter(fw);	
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		System.out.println( "num of option to check : "+ how_many_option_to_run());
		long answars=0;
		boolean next = build_empty_department();
		while( next == true )
		{
			answars++;
			//write_to_file_an_open_file(Department_toString());
			//System.out.println( "succeed "+ answars);
			next = build_next_department();	
		}
		close_open_file_file();
		
		System.out.println(" i have found "+ answars + " possibole answars");
	}
	public void print_Department(){
		for (int j = 0; j < models.length; j++) {
			models[j].print_model();
		}
		for (int i = 0; i < teachers.length; i++) {
			teachers[i].print_teacher();
		}
	}
	public String Department_toString(){
		String  ans = "";

		for (int i = 0; i < models.length; i++) {
			ans = ans +"M"+i + models[i].model_to_String();
		}
		return ans+"\n";
	}
	
	/*public boolean build_next_department(int index_of_model_failed){
		//not suposed to happend
		if(index_of_model_failed == 0 )return false;
		else
		{
			models[ index_of_model_failed ].clear_model();

		}


	}*/
	public void write_to_file_an_open_file(String content){

		try {

			bw.write(content);
		} 
		catch (IOException e)
		{

			e.printStackTrace();
		}

	}
	public void close_open_file_file(){
		try 
		{
			if (bw != null) bw.close();
			if (fw != null)	fw.close();
		} 
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
	public void set_department(String txtFile){
		clear_department();
		int m = 0, c = 0 ,d = 0 , h = 0,t = 0;
		//M0C0D0H0T0C1D1H0T0
		char current;
		String val;
		int j;
		for (int i = 0; i < txtFile.length(); i++) {
			current = txtFile.charAt(i);
			
			if( current == 'M')
			{
				i++;
				j = txtFile.indexOf("C",i);				
				//System.out.println("i:"+ i+ " j:"+ j +" len: "+txtFile.length());
				val = txtFile.substring(i, j);
				m = Integer.parseInt(val);
				i = j-1;
			}
			else if( current == 'C')
			{
				i++;
				
				j = txtFile.indexOf("D",i);				
				val = txtFile.substring(i, j);
				c = Integer.parseInt(val);
				i = j-1;
			}
			else if( current == 'D')
			{
				i++;
				j = txtFile.indexOf("H",i);				
				val = txtFile.substring(i, j);
				d = Integer.parseInt(val);
				i = j-1;
			}
			else if( current == 'H')
			{
				i++;
				j = txtFile.indexOf("T",i);				
				val = txtFile.substring(i, j);
				h = Integer.parseInt(val);
				i = j-1;
			}
			else if( current == 'T')
			{
				i++;
				j = txtFile.indexOf("|",i);
				val = txtFile.substring(i, j);
				t = Integer.parseInt(val);
				i = j-1;
				models[m].schedual_course(c, d, h,t);
			}		
			
		}

		
	}
	public void clear_department(){
		for (int i = 0; i < models.length; i++) 
		{
			models[i].clear_model();
		}
			schedual_Department = false;
	}
	public BigInteger how_many_option_to_run(){
		BigInteger ans = new BigInteger("1");
		
		//ni = ni.multiply( new BigInteger("4") );
	

		BigInteger tmp_BG =  new BigInteger("1") ;
		String tmp_s="";
		for (int i = 0; i < models.length; i++) {
			tmp_s = ""+  models[i].MODEL_option_to_runa();
			tmp_BG = new BigInteger(tmp_s);
			ans = ans.multiply(tmp_BG); 
					
		}
		return ans;
	}	
	
	public static void main(String[] args) {
	
		// TODO Auto-generated method stub
		/*int[][] forbididen_hours1 = {{1,9},{1,10},{1,11},{1,12},{1,13},{1,14},{4,14},{4,15},{4,16},{4,17},{4,18},{4,19},{4,20},{4,21}};
	int[][] forbididen_hours2 = {{3,9},{3,10},{3,11},{3,12},{3,13},{3,14},{1,14},{1,15},{1,16},{1,17},{1,18},{1,19},{1,20},{1,21}};
	int[][] forbididen_hours3 = {{5,9},{5,10},{5,11},{5,12},{5,13},{5,14},{5,15},{5,16},{5,17},{5,18},{5,19},{5,20},{5,21}};
	int[][] forbididen_hours4 = {{3,9},{3,10},{3,11},{3,12},{3,13},{3,14},{3,15},{3,16},{3,17},{3,18},{3,19},{3,20},{3,21}};
	Teacher Aviv = new Teacher(1, 4, 1, forbididen_hours1);
	Teacher Yona= new Teacher(2, 6, 2, forbididen_hours2);
	Teacher Noam= new Teacher(3, 10, 0, forbididen_hours3);
	Teacher Dan= new Teacher(4, 6, 6, forbididen_hours4);

	Teacher[] T = {Aviv, Yona, Noam, Dan};

	Course Bdida = new Course(1, 5, new int[]{1,2});
	Course Logic = new Course(2, 5, new int[]{1,2});
	Course DB = new Course(3, 3, new int[]{0,3});
	Course OS = new Course(4, 3, new int[]{2,0});
	Course Infy = new Course(5, 5, new int[]{1,3});

	Course [] C = {Bdida, Logic, DB, OS, Infy};

		int k=0;
		while(k<89){
			for (int i = 9; i < 22; i++) {
				forbididen_hours1[k][1]= i;
				forbididen_hours2[k][1]= i;
				k++;
			}

		}
		k=0;

		for (int i = 9; i < 22; i++) {
			forbididen_hours1[k][0]= 0;
			forbididen_hours2[k][0]= 0;
			k++;	
		}
		for (int i = 9; i < 22; i++) {
			forbididen_hours1[k][0]= 1;
			forbididen_hours2[k][0]= 1;
			k++;	
		}for (int i = 9; i < 22; i++) {
			forbididen_hours1[k][0]= 2;
			forbididen_hours2[k][0]= 2;
			k++;	
		}for (int i = 9; i < 22; i++) {
			forbididen_hours1[k][0]= 3;
			forbididen_hours2[k][0]= 3;
			k++;	
		}for (int i = 9; i < 22; i++) {
			forbididen_hours1[k][0]= 4;
			forbididen_hours2[k][0]= 4;
			k++;	
		}for (int i = 9; i < 22; i++) {
			forbididen_hours1[k][0]= 5;
			forbididen_hours2[k][0]= 5;
			k++;	
		}

		 */

		//int[][] forbididen_hours1 = {{6,17}};
		//int[][] forbididen_hours2 = {{6,17}};

		//Teacher Aviv = new Teacher("Aviv",1, 4, 1, forbididen_hours1);
		//Teacher Yona= new Teacher("Yona",2, 6, 2, forbididen_hours2);
		
		//Aviv.T_scheduale.fullSched();
		//Aviv.T_scheduale.schedule[1][5]=false;
		//Aviv.T_scheduale.schedule[0][2]=false;
		//Aviv.T_scheduale.print();
		//Yona.T_scheduale.fullSched();
		//Yona.T_scheduale.schedule[0][0]=false;
		//Yona.T_scheduale.schedule[3][5]=false;
		//Yona.T_scheduale.print();
		Teacher Aviv = new Teacher("Aviv");
		Teacher Yona= new Teacher("Yona");
		Teacher Eli = new Teacher("Eli");
		Teacher Yossi= new Teacher("Yossi");
		Teacher[] T = {Aviv,Yona};

		Course Bdida = new Course(/*1,*/"Bdida", 7, new Teacher[]{Aviv});
		Course Logic = new Course(/*2,*/"Logic", 7, new Teacher[]{Yona});
		Course infi = new Course(/*1,*/"infi", 6, new Teacher[]{Yossi});
		Course OS = new Course(/*2,*/"os", 6, new Teacher[]{Eli});
		Course infi2 = new Course(/*1,*/"infi2", 6, new Teacher[]{Yossi});
		Course OS2 = new Course(/*2,*/"os2", 6, new Teacher[]{Eli});

		Course [] C1 = {Bdida, Logic,infi2,OS2};
		Course [] C2 = {infi, OS};
		Model M1 = new Model (C1/*,T*/);
		Model M2 = new Model (C2/*,T*/);
		Model[] models = {M1/*,M2*/};
		Department CS = new Department(models, T);
		//System.out.println(CS.how_many_option_to_run());
		CS.build_all_departments();
		//CS.set_department("M0C0D0H0T0|C1D1H0T0|M1C0D0H0T0|C1D1H0T0|");
		//CS.print_Department();
		//CS.write_to_file_an_open_file("aa");
		//CS.write_to_file_an_open_file("bb");
		/*M.print_model();
		System.out.println("teachers:");
		for (int i = 0; i < T.length; i++) {
			T[i].print_teacher();
		}*/
		/*System.out.println("model:");
		M.M_scheduale.print();
		System.out.println();
		System.out.println("yona:");
		Yona.T_scheduale.print();
		System.out.println();
		System.out.println("Aviv:");
		Aviv.T_scheduale.print();
		System.out.println("bdida");
		Bdida.print();
		System.out.println("logic");
		Logic.print();*/
		/*for (int i = 0; i < T.length; i++) {
		T[i].T_scheduale.print();
	}*/

	}

}
