package pres;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Pres22 {
    public static void main(String[] args) throws Exception{
        //DaoImpl dao=new DaoImpl();
        Scanner scanner=new Scanner(System.in);
        System.out.print("Donner la classe de lma couche DAO:");
        String daoClassName=scanner.nextLine();
        Class cDao=Class.forName(daoClassName);
        IDao dao=(IDao) cDao.getConstructor().newInstance(); // => new DaoImpl()

        //MetierImpl metier=new MetierImpl();
        System.out.print("Donner la classe de lma couche Métier:");
        String metierClassname=scanner.nextLine();
        Class cMetier=Class.forName(metierClassname);
        IMetier metier=(IMetier) cMetier.getConstructor().newInstance();

        //metier.setDao(dao); // Statique
        Method setDao=cMetier.getDeclaredMethod("setDao",IDao.class);
        setDao.invoke(metier,dao); // Injection des dépendances dynamique

        System.out.println("RES="+metier.calcul());

    }
}
