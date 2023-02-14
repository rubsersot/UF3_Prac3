package uf3_prac3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Utils;

public class Exercici2 {

    private static class Clients{
        int codi;
        String nom;
        String cognoms;
        int diaNaixement;
        int mesNaixement;
        int anyNaixement;
        String adrecaPostal;
        String email;
        boolean vip;
    }
    
    public static Scanner scan = new Scanner(System.in);
    static File clients = new File("clients.bin");
    
    public static void main(String[] args) {
        crearFitxer();
        
        mostrarMenu();
        
        int opcio = Utils.LlegirInt();
        gestionarOpcions(opcio);
        
        scan.close();
    }
    
    private static void crearFitxer(){
        if(!clients.exists()){
            try {
                clients.createNewFile();
            } catch (IOException ex) {
                System.out.println("ERROR!, no s'ha pogut crear el fitxer");
            }
        }
    }
    
    private static void gestionarOpcions(int opcio){
        Clients client = new Clients();
        while(opcio != 0){
            switch (opcio) {
                case 1:
                    altaClient(client);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    mostrarDades();
                    break;
                default:
                    System.out.println("ERROR, opció no vàlida");
                    break;
            }
            mostrarMenu();
            opcio = Utils.LlegirInt();
        }
    }
    
    private static void mostrarMenu(){
        System.out.println("Menu");
        System.out.println("0. Sortir del programa");
        System.out.println("1. Alta d'un client");
        System.out.println("2. Consulta d'un client per posició");
        System.out.println("3. Consulta d'un client per codi");
        System.out.println("4. Modificar un client");
        System.out.println("5. Esborrar un client");
        System.out.println("6. Llistar tots els clients");
        System.out.print("Introdueix una opció: ");
    }
    
    private static void altaClient(Clients client){
        client = demanarDades(client);
        afegirDades(client);
    }
    
    private static void afegirDades(Clients client){
        try {
            FileOutputStream fos = new FileOutputStream(clients, true);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeInt(client.codi);
            dos.writeUTF(client.nom);
            dos.writeUTF(client.cognoms);
            dos.writeInt(client.diaNaixement);
            dos.writeInt(client.mesNaixement);
            dos.writeInt(client.anyNaixement);
            dos.writeUTF(client.adrecaPostal);
            dos.writeUTF(client.email);
            dos.writeBoolean(client.vip);
            dos.flush();
            dos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("El fitxer no existeix");
        } catch (IOException ex) {
            Logger.getLogger(Exercici2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static Clients demanarDades(Clients client){
        client.codi = Utils.LlegirInt("Introdueix el codi: ");
        System.out.print("Introdueix el nom: ");
        client.nom = scan.nextLine();
        System.out.print("Introdueix els cognoms: ");
        client.cognoms = scan.nextLine();
        client.diaNaixement = Utils.LlegirInt("Introdueix el dia de naixement: ");
        client.mesNaixement = Utils.LlegirInt("Introdueix el mes de naixement: ");
        client.anyNaixement = Utils.LlegirInt("Introdueix l'any de naixement: ");
        System.out.print("Introdueix l'adreça postal: ");
        client.adrecaPostal = scan.nextLine();
        System.out.print("Introdueix l'email: ");
        client.email = scan.nextLine();
        client.vip = llegirVip();
        return client;
    }
    
    private static boolean llegirVip(){
        boolean vip = false;
        boolean valid = false;
        System.out.println("Introdueix si és VIP (si/no): ");
        String input = scan.nextLine();
        while(!valid){
            switch (input) {
                case "si":
                    vip = true;
                    valid = true;
                    break;
                case "no":
                    vip = false;
                    valid = true;
                    break;
                default:
                    System.out.print("ERROR, entrada no vàlida, introdueix (si/no): ");
                    input = scan.nextLine();
                    break;
            }
        }
        
        return vip;
    }
    
    private static void mostrarDades(){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(clients);
            DataInputStream dis = new DataInputStream(fis);
            System.out.println("Codi: " + dis.readInt());
            System.out.println("Nom: " + dis.readUTF());
            System.out.println("Cognoms: " + dis.readUTF());
            System.out.println("Data de Naixement (DD/MM/YYYY): " + dis.readInt() + "/" + dis.readInt() + "/" + dis.readInt());
            System.out.println("Adreça postal: " + dis.readUTF());
            System.out.println("E-mail: " + dis.readUTF());
            boolean vip = dis.readBoolean();
            System.out.print("VIP: ");
            if(vip) System.out.println("Si");
            else System.out.println("No");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Exercici2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Exercici2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Exercici2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private static void llistarClients(){
        
    }
    
}
