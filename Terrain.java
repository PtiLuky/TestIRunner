/*
 * 
 * Classe sur la creation et gestion du terrain
 * 
 * Pour les algorithmes, se referer aux versions papier, c'est colore, trop beau *-* (et plus clair)
 */

abstract class Terrain {
//genere un terrain pour le debut
	
/**
 * Cree le debut du terrain - simple pour demarrer 
 * @param terrain : terrain a modifier
 */
	public static void initTerrain(Objet[][] terrain){
		int longueurDebut=9;
		int i;
		for(int j=0;j<longueurDebut;j++){
			i=1;
			terrain[i][j]=new Objet("tiles/H.png",'H',96*j,96*i,0,0);
			i=2;
			terrain[i][j]=new Objet("tiles/O.png",'O',96*j,96*i,0,0);
			i=3;
			terrain[i][j]=new Objet("tiles/O.png",'O',96*j,96*i,0,0);
			i=4;
			terrain[i][j]=new Objet("tiles/K.png",'K',96*j,96*i,0,0);
			i=5;
			terrain[i][j]=new Objet("tiles/O.png",'O',96*j,96*i,0,0);
			i=6;
			terrain[i][j]=new Objet("tiles/O.png",'O',96*j,96*i,0,0);
			i=7;
			terrain[i][j]=new Objet("tiles/B.png",'B',96*j,96*i,0,0);
		}
		for(int k=longueurDebut;k<terrain[0].length;k++)
			generTerrain(terrain,k,0);
	}
	
/**
 * met a jour le terrain et decale tout si besoin
 * @param terrain : terrain a modifier
 */
	public static void gestionTerrain(Objet[][] terrain){
		if(terrain[1][1].x<0){ // si une colonne est entierement cachee
			for(int j=0;j<terrain[0].length-1;j++)
				for(int i=1;i<terrain.length;i++)
					terrain[i][j]=terrain[i][j+1];
			generTerrain(terrain,terrain[0].length-1,terrain[1][0].x);
		}
	}
	
/**
 * genere une colonne du terrain en fonction de la precedente
 * @param terrain : terrain a modifier
 * @param j : 0<j<11, colonne a creer
 * @param decal : decalage de l'abscisse de la colonne
 */
	public static void generTerrain(Objet[][] terrain,int j,int decal){ 
		int random;
		char lettre;
		int i;
		
//cases vides
		i=3;
		terrain[i][j]=new Objet("tiles/O.png",'O',96*j+decal,96*i,0,0);
		i=5;
		terrain[i][j]=new Objet("tiles/O.png",'O',96*j+decal,96*i,0,0);

//case centrale :
		i=4;
		random=(int)(Math.random()*2);
		if(terrain[i][j-1].type=='O')
			lettre=(random==0)?'O':'J';
		else if(terrain[i][j-1].type=='L')
			lettre='O';
		else
			lettre=(random==0)?'K':'L';	
		terrain[i][j]=new Objet("tiles/"+lettre+".png",lettre,96*j+decal,96*i,0,0);
		
//seconde haut :
		i=2;
		if(terrain[i-1][j-1].type=='I'||terrain[i-1][j-1].type=='F'||terrain[i][j-1].type=='I'||terrain[i][j-1].type=='N')
			lettre='O';
		else if(terrain[i][j-1].type=='O'){
			random=(int)(Math.random()*3);
			if(random==0)
				lettre='O';
			else if(random==1)
				lettre='G';
			else
				lettre='N';
		}else{
			random=(int)(Math.random()*2);
			lettre=(random==0)?'H':'I';
		}
		terrain[i][j]=new Objet("tiles/"+lettre+".png",lettre,96*j+decal,96*i,0,0);
		
//premiere haut :
		i=1;
		if(terrain[i+1][j].type=='O'){
			if(terrain[i][j-1].type=='O'){
				random=(int)(Math.random()*3);
				lettre=(random<2)?'G':'O';
			}else if(terrain[i][j-1].type=='G'||terrain[i][j-1].type=='H'||terrain[i][j-1].type=='D'||terrain[i][j-1].type=='E'){
				random=(int)(Math.random()*3);
				lettre=(random<2)?'H':'I';
			}else if(terrain[i][j-1].type=='I'||terrain[i][j-1].type=='F')
				lettre='O';
		}else if(terrain[i+1][j].type=='G'||terrain[i+1][j].type=='N'){
			if(terrain[i][j-1].type=='O')
				lettre='D';
			else if(terrain[i][j-1].type=='G'||terrain[i][j-1].type=='H')
					lettre='E';
		}else if(terrain[i+1][j].type=='H')
			lettre='E';
		else if(terrain[i+1][j].type=='I'){
			random=(int)(Math.random()*3);
			lettre=(random<2)?'E':'F';
		}
		terrain[i][j]=new Objet("tiles/"+lettre+".png",lettre,96*j+decal,96*i,0,0);

//seconde bas :
		i=6;
		if(terrain[i+1][j-1].type=='C'||terrain[i+1][j-1].type=='F'||terrain[i][j-1].type=='C'||terrain[i][j-1].type=='M')
			lettre='O';
		else if(terrain[i][j-1].type=='O'){
			random=(int)(Math.random()*3);
			if(random==0)
				lettre='O';
			else if(random==1)
				lettre='A';
			else
				lettre='M';
		}else{
			random=(int)(Math.random()*2);
			lettre=(random==0)?'B':'C';
		}
		terrain[i][j]=new Objet("tiles/"+lettre+".png",lettre,96*j+decal,96*i,0,0);

//premiere bas :
		i=7;
		if(terrain[i-1][j].type=='O'){
			if(terrain[i][j-1].type=='O'){
				random=(int)(Math.random()*3);
				lettre=(random<2)?'A':'O';
			}else if(terrain[i][j-1].type=='A'||terrain[i][j-1].type=='B'||terrain[i][j-1].type=='D'||terrain[i][j-1].type=='E'){
				random=(int)(Math.random()*3);
				lettre=(random<2)?'B':'C';
			}else if(terrain[i][j-1].type=='C'||terrain[i][j-1].type=='F')
				lettre='O';
		}else if(terrain[i-1][j].type=='A'||terrain[i-1][j].type=='M'){
			if(terrain[i][j-1].type=='O')
				lettre='D';
			else if(terrain[i][j-1].type=='A'||terrain[i][j-1].type=='B')
					lettre='E';
		}else if(terrain[i-1][j].type=='B')
			lettre='E';
		else if(terrain[i-1][j].type=='C'){
			random=(int)(Math.random()*3);
			lettre=(random<2)?'E':'F';
		}
		terrain[i][j]=new Objet("tiles/"+lettre+".png",lettre,96*j+decal,96*i,0,0);
		
	}
	
	
}
