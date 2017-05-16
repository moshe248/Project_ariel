import java.math.BigInteger;

public class my_main {
	public void init_moria(){
		Teacher Jeffrey_Kantor = new Teacher("Jeffrey_Kantor");
		Teacher Chen_Shasho = new Teacher("Chen_Shasho");
		Teacher Simcha_Lev = new Teacher("Simcha_Lev");
		Teacher Amitay_Tauob = new Teacher("Amitay_Tauob");
		Teacher Amit_Kolin = new Teacher("Amit_Kolin");
		Teacher Eyal_Assayag = new Teacher("Eyal_Assayag");
		Teacher Eyal_Ekhaous = new Teacher("Eyal_Ekhaous");
		Teacher Shaoli_Sharf = new Teacher("Shaoli_Sharf");
		Teacher Refael_Almog = new Teacher("Refael_Almog");
		Teacher Shmoel_Choen = new Teacher("Shmoel_Choen");
		Teacher Chagay_Gross = new Teacher("Chagay_Gross");
		Teacher Limor_Gonen = new Teacher("Limor_Gonen");
		Teacher Zeev_Shtodiner = new Teacher("Zeev_Shtodiner");
		Teacher Idit_Solberg = new Teacher("Idit_Solberg");
		Teacher Zoya_Nisanov = new Teacher("Zoya_Nisanov");
		Teacher Amos_Baranes = new Teacher("Amos_Baranes");
		Teacher Elad_Kaley = new Teacher("Elad_Kaley");
		Teacher Galit_Klain = new Teacher("Galit_Klain");
		Teacher Eran_Fishman = new Teacher("Eran_Fishman");
		Teacher Shola_Pessach = new Teacher("Shola_Pessach");
		Teacher Artiom_Jelnov = new Teacher("Artiom_Jelnov");
		Teacher Yehoda_Ashkenazi = new Teacher("Yehoda_Ashkenazi");
		Teacher Roman_Yavitz = new Teacher("Roman_Yavitz");
		
		Teacher [] teachers = {Amit_Kolin, Amitay_Tauob, Amos_Baranes, Artiom_Jelnov,
				Chagay_Gross, Chen_Shasho, Elad_Kaley, Eran_Fishman, Eyal_Assayag, Eyal_Ekhaous,
				Galit_Klain, Idit_Solberg, Jeffrey_Kantor, Limor_Gonen, Refael_Almog, Roman_Yavitz,
				Shaoli_Sharf, Shmoel_Choen, Shola_Pessach, Simcha_Lev, Yehoda_Ashkenazi,
				Zeev_Shtodiner, Zoya_Nisanov};
		
		Teacher[] T1 = {Limor_Gonen, Zeev_Shtodiner};
		Course Micro_Introduction = new Course ("Micro_Introduction", 5, T1); 
		Teacher[] T2 = {Yehoda_Ashkenazi, Roman_Yavitz};
		Course Math_Economic_A  = new Course ("Math_Economic_A", 3, T2);
		Teacher[] T3 = {Artiom_Jelnov,Elad_Kaley};
		Course Statistics_A = new Course ("Statistics_A", 4, T3); 
		Teacher[] T4 = {Chen_Shasho};
		Course Finance_Introduction = new Course ("Finance_Introduction", 3, T4); 
		Teacher[] T5 = {Eyal_Assayag, Eyal_Ekhaous};
		Course Information_Systems = new Course ("Information_Systems", 3, T5); 
		Teacher[] T6 = {Shaoli_Sharf, Refael_Almog};
		Course Laws  = new Course ("Laws", 2, T6); 
		Teacher[] T7 = {Shaoli_Sharf};
		Course Business_Law_A = new Course ("Business_Law_A", 2, T7); 
		Teacher[] T8 = {Zeev_Shtodiner, Zoya_Nisanov, Shola_Pessach};
		Course Pricing_Theory_A  = new Course ("Pricing_Theory_A", 5, T8); 
		Teacher[] T9 = {Idit_Solberg, Shola_Pessach};
		Course Macro_Economics_A = new Course ("Macro_Economics_A", 3, T9); 
		Teacher[] T10 = {Amos_Baranes, Elad_Kaley};
		Course Econometrics_A  = new Course ("Econometrics_A", 3, T10); 
		Teacher[] T11 = {Galit_Klain};
		Course Research_Methods_A = new Course ("Research_Methods_A", 2, T11); 
		Teacher[] T12 = {Amitay_Tauob, Chen_Shasho};
		Course Financial_Problems_A  = new Course ("Financial_Problems_A", 5, T12); 
		Teacher[] T13 = {Amit_Kolin, Chagay_Gross};
		Course Management_Accounting_A = new Course ("Management_Accounting_A", 3, T13); 
		Teacher[] T14 = {Amit_Kolin, Shmoel_Choen};
		Course Funding_B  = new Course ("Funding_B", 3, T14);
		Teacher[] T15 = {Simcha_Lev};
		Course Taxes_A = new Course ("Taxes_A", 3, T15); 
		Teacher[] T16 = {Jeffrey_Kantor};
		Course Accounting_Seminar = new Course ("Accounting_Seminar_A", 2, T16); 
		Teacher[] T17 = {Zoya_Nisanov,Zeev_Shtodiner};
		Course Economics_Seminar = new Course ("Economics_Seminar_A", 2, T17); 
		Teacher[] T18 = {Amitay_Tauob};
		Course Advanced_Accounting_A  = new Course ("Advanced_Accounting_A", 6, T18); 
		Teacher[] T19 = {Idit_Solberg};
		Course Israeli_Economy = new Course ("Israeli_Economy", 2, T19); 
		Teacher[] T20 = {Simcha_Lev};
		Course Taxes_C  = new Course ("Taxes_C", 3, T20); 
		Teacher[] T21 = {Eyal_Assayag};
		Course Criticisem_B = new Course ("Criticisem_B", 3, T21);
		
		Course [] A = {Micro_Introduction, Math_Economic_A, Statistics_A, Finance_Introduction, 
				Business_Law_A, Laws, Information_Systems}; 
		Course [] B = {Pricing_Theory_A, Macro_Economics_A, Econometrics_A, Research_Methods_A,
				Financial_Problems_A, Management_Accounting_A, Funding_B, Taxes_A};
		Course [] C = {Economics_Seminar, Accounting_Seminar, Advanced_Accounting_A,
				Israeli_Economy, Taxes_C, Criticisem_B};
		Model Year_A = new Model(A);
		Model Year_B = new Model(B);
		Model Year_C = new Model(C);
		
		Model [] models = {Year_A, Year_B, Year_C};
		
		Department Accounting_Semester_A = new Department(models, teachers);
	//	System.out.println(Time);
		//Accounting_Semester_A.build_all_departments();
		System.out.println( Accounting_Semester_A.how_many_option_to_run() );
	}

	public static void main(String[] args) {
		
		my_main m =  new my_main();
		m.init_moria();
		
	}

}
