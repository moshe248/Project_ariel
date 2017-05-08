//// ���� ������ ��� ������ �������� ��� �������� �������� �� 
//���� ������ ��� ������ ��� ������� ����� ������� ��� ��� ������ �� �� ��� ���� ����
// ���� ����� �� ��� ��� ����� ������ �� ���� ������ ��� ���� ��"� ���� ���� ������ ����� �� ���� ����� ������� ��� ����
public class Department {
	Model[] models;
	Teacher[] teachers;
	//int save_dayIndex = 0;
	//int save_hourIndex = 0;

	public Department(Model[] models, Teacher[] teachers){
		this.models = models;
		this.teachers = teachers;
	}

	/*public void run_app(){

		for (int i = 0; i < models.length; i++) {
			models[i].build_model();
		}
	}*/

	//���� ����� ����� ��� �� ����� ���� ���� ���� ���� ���
	public void build_department(){
		boolean possibole = true;;
		for (int i = 0; i < models.length && possibole == true; i++) {
			possibole= models[i].schedule_Model();
		}
		if(possibole == true) System.out.println( "concradulation ");
		else System.out.println(" you faild");
	}
	public boolean build_department2(){
		return build_department3(0);
	}	
	public boolean build_department3(int empty_model_index){
		boolean build_model ;
		int i = empty_model_index;
		while ( i > -1 && i < models.length   )		
		{
			build_model = models[i].schedule_Model();
			if( build_model == true) i++;
			else 
			{
			//	if( i == 0 )i--;// it means that it is not possible to advance and sched even the first model	
				while(i > -1 && build_model == false )//erase backward
				{	
					models[i].clear_model();
					i--;
					if( i > -1 )build_model = models[i].build_next_model();
				}
				if( build_model == true) i++;				
			}
		}
		if( i == -1 )
		{
			System.out.println("you did not succeed building all models ");
			return false;
		}
		if( i == models.length  ){
			System.out.println("you succeed building all models! concradulation! ");
			System.out.println("*******************************************************************************************");
			for (int j = 0; j < models.length; j++) {
				models[j].print_model();
			}
		}
		return true;
	}	
	public boolean build_next_department2(/*void*/ ){
		boolean build_model =false;
		int i = find_last_build_model();
		//if i = -1 ? can i=-1?

		while ( i > -1 && i < models.length && build_model == false   )		
		{
			build_model = models[i].build_next_model();

			if(build_model == false )
			{
				models[i].clear_model();
				i--;				
			}			
			else  i++;			
		}
		if( build_model == true ) return build_department3(i);
		else System.out.println( "no more option");	
		return false;
		/*
		boolean build_model =false;
		int i = models.length-1;	

		while ( i > -1 && i < models.length && build_model == false   )		
		{
			build_model = models[i].build_next_model();

			if(build_model == false )
			{
				models[i].clear_model();
				i--;				
			}			
			else  i++;			
		}
		if( build_model == true ) return build_department3(i);
		else System.out.println( "no more option");	
		return false;*/
	}	
	public int find_last_build_model(){
		int i; 	
		for ( i =  models.length-1; i> -1 ; i--) {
			if(models[i].is_schedule) break;
		}
		return i;
	}
	public void build_all_departments(){
		int answars=0;
		boolean next = build_department2();
		while( next == true && answars < 500)
		{
		answars++;
		next= build_next_department2();	
		}
	
		System.out.println(" i have found "+ answars + " possibole answars");
	}
	/*public boolean build_next_department(int index_of_model_failed){
		//not suposed to happend
		if(index_of_model_failed == 0 )return false;
		else
		{
			models[ index_of_model_failed ].clear_model();

		}


	}*/

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

		int[][] forbididen_hours1 = {{1,9}};
		int[][] forbididen_hours2 = {{1,9}};

		Teacher Aviv = new Teacher("Aviv",1, 4, 1, forbididen_hours1);
		Teacher Yona= new Teacher("Yona",2, 6, 2, forbididen_hours2);
		Aviv.T_scheduale.fullSched();
		Aviv.T_scheduale.schedule[1][5]=false;
		Aviv.T_scheduale.schedule[0][2]=false;
		//Aviv.T_scheduale.print();
		Yona.T_scheduale.fullSched();
		Yona.T_scheduale.schedule[0][0]=false;
		Yona.T_scheduale.schedule[3][5]=false;
		Teacher[] T = {Aviv,Yona};

		Course Bdida = new Course(/*1,*/"Bdida", 1, new int[]{0,1});
		Course Logic = new Course(/*2,*/"Logic", 1, new int[]{0});

		Course [] C = {Bdida, Logic};
		Model M = new Model (C,T);
		Model[] models = {M};
		Department CS = new Department(models, T);




		//CS.build_department();
		//CS.build_department2();
		CS.build_all_departments();

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
