package com.monstersaku.util;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player {
    private String name;
    public List<Monster> listMonster; // set public karena atribut monster dapat dipengaruhi method dari luar kelas
    public Monster currMonster; // set public karena atribut monster dapat dipengaruhi method dari luar kelas
    private int countMonster;
    
    public Player(String name, List<Monster> listMonster) {
        this.name = name;
        this.listMonster = listMonster;
        this.currMonster = listMonster.get(0);
        this.countMonster = listMonster.size();
    }

    // Getter & Setter
    public String getName() {
        return this.name;
    }

    public List<Monster> getListMonster() {
        return this.listMonster;
    }

    public Monster getCurMonster() {
        return this.currMonster;
    }

    public int getCountMonster() {
        return this.countMonster;
    }

    public void setCountMonster(int countMonster) {
        this.countMonster = countMonster;
    }

    // method
    public static void start(){                                                                                                              
        Scanner sc = new Scanner(System.in);
        System.out.println(  "  _|_|_|     _|_|     _|      _|   _|_|_|_|         _|_|_|   _|_|_|_|_|     _|_|     _|_|_|     _|_|_|_|_|");
        System.out.println(  "_|         _|    _|   _|_|  _|_|   _|             _|             _|       _|    _|   _|    _|       _|    ");   
        System.out.println(  "_|  _|_|   _|_|_|_|   _|  _|  _|   _|_|_|           _|_|         _|       _|_|_|_|   _|_|_|         _|    ");   
        System.out.println(  "_|    _|   _|    _|   _|      _|   _|                   _|       _|       _|    _|   _|    _|       _|    ");   
        System.out.println(  "  _|_|_|   _|    _|   _|      _|   _|_|_|_|       _|_|_|         _|       _|    _|   _|    _|       _|    ");
        help();
    }
    public static void startgame(){                                                                                                              
        Scanner sc = new Scanner(System.in);                                                                                                           
        System.out.println("\n-- Pilih Menu Berikut --");
        System.out.println("   1. Start");
        System.out.println("   2. Help");
        System.out.println("   3. Exit");
        System.out.println("Menu yang kamu pilih : ");  
        
        int start = sc.nextInt();
        if (start == 1){
            
            
        }
        else if (start == 2){
            help();
            startgame();
        }
        else if (start == 3){
            System.out.println("Anda keluar dari game");
            System.exit(0);
        }
        else{
            System.out.println("Input kamu salah!!!");
            startgame();
        }

    }

    public static void help(){
        System.out.println("---Deskripsi Permainan---");
        System.out.println("Game ini dimainkan oleh dua pemain dalam satu komputer secara bergantian. Ketika permainan dimulai masing - masing pemain akan mendapatkan 6 monster acak lalu memasuki fase pertarungan. Dalam fase ini pemain dapat bergantian memilih aksi yang akan dilakukan. Pemain memiliki dua pilihan untuk bergerak atau berganti monster. Ketika pemain menggunakan move(bergerak) pemain dapat memilih gerakan yang tersedia pada menu yang akan muncul. Pemain pertama yang berhasil mengalahkan semua monster lawan sebelum semua monsternya dikalahkan akan menjadi pemenangnya. ");
        System.out.println("=====================================");
        System.out.println("---Fungsi Command---");
        System.out.println("1. Anda dapat memilih menu dengan memasukan angka yang berada disebelah kiri menu ");
        System.out.println("2. Untuk menampilkan daftar monster anda dapat memilih menu 'View Monster Info' setiap awal turn");
        System.out.println("3. Untuk menampilkan informasi game saat ini anda dapat memilih menu 'View Game Info' setiap awal turn");
        System.out.println("4. Ketika anda siap untuk melakukan aksi pilih menu 'Continue Game' setiap awal turn");
        System.out.println();
    }

    
    

    public static void newTurn() {
        System.out.println("_|    _|  _|_|_|_|  _|         _|      _|_|_|_|_|  _|      _|  _|_|_|    _|    _|");
        System.out.println("_|_|  _|  _|         _|       _|           _|      _|      _|  _|    _|  _|_|  _|");
        System.out.println("_| _| _|  _|_|_|     _|  _|   _|           _|      _|      _|  _|_|_|    _| _| _|");
        System.out.println("_|  _|_|  _|          _|_| _|_|            _|      _|      _|  _|   _|   _|  _|_|");
        System.out.println("_|    _|  _|_|_|_|     _|   _|             _|        _|_|_|    _|    _|  _|    _|");
    }
    

    public void printGameInfo(Monster currmon){
        int idx = 1;
        for (Monster m : listMonster) {
            if(m.equals(currmon)){
                System.out.println(String.valueOf(idx) + ". " + m.getNama()+" --On Field");
            }
            else if(m.isIsDead()==true){
                System.out.println(String.valueOf(idx) + ". " + m.getNama()+" --Fainted");
            }
            else{
                System.out.println(String.valueOf(idx) + ". " + m.getNama()); // liat method getName Monster
            } 
            idx++;       
        }
    } 

    public Monster switchMenu(Monster currmon) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n-- MEMILIH MENU SWITCH --");
        System.out.println("-- Pilih Monster yang akan kamu gunakan --");

        int idx = 1;
        for (Monster m : listMonster) {
            if(m.equals(currmon)){
                System.out.println(String.valueOf(idx) + ". " + m.getNama()+" --On Field");
            }
            else if(m.isIsDead()==true){
                System.out.println(String.valueOf(idx) + ". " + m.getNama()+" --Fainted");
            }
            else{
                System.out.println(String.valueOf(idx) + ". " + m.getNama()); // liat method getName Monster
            } 
            idx++;       
        }

        System.out.println("Monster yang kamu pilih : ");

        int index = sc.nextInt();
        if (this.listMonster.get(index - 1)==currmon){
            System.out.println("Masukan indeks monster yang tidak sedang digunakan");
            switchMenu(currmon);
            return(currmon);
        }
        else if(this.listMonster.get(index - 1).isIsDead() == true){
            System.out.println("Monster dengan indeks ini telah mati silahkan masukan ideks lagi");
            switchMenu(currmon);
            return(currmon);
        }
        else{
            try {
                this.currMonster = this.listMonster.get(index - 1);
                System.out.println("-- Memilih Monster " + currMonster.getNama() + " --"); // liat method getName Monster
                currmon = currMonster;
                return(currmon);
            } catch (Exception e) {
                System.out.println("Index yang kamu masukkan salah!!!");
                switchMenu(currmon);
                return(currmon);
            }
        }
        
    }
    
    public void beforeEffect() {
        // prosedur dijalankan pada setiap giliran
        Random rand = new Random();
        for (int z = 0; z < listMonster.size(); z++){
            if (listMonster.get(z).sleepCounter > 0){
                listMonster.get(z).sleepCounter--;
            }
            else{
                if (listMonster.get(z).getStatus().equals(StatusCondition.SLEEP)){
                    listMonster.get(z).setStatus(StatusCondition.NONE);
                }
            }
            
        } 
    }

    public void afterEffect(){
        StatusCondition status = currMonster.getStatus(); // akses status condition dari monster
        System.out.println(currMonster.getStatus());
        if (status.equals(StatusCondition.BURN) ) {
            // BURN
            Stats base = currMonster.getBaseStats();
            if((base.getHP() - base.getMaxHP() * 0.125)<=0){
                base.setHP(0);
                currMonster.setIsDead(true);
                System.out.println(currMonster.getNama()+" burned to death");
            } 
            else{
                base.setHP(base.getHP() - base.getMaxHP() * 0.125);
            }
            currMonster.setBaseStats(base);

        } else if (status.equals(StatusCondition.POISON)) {
            // POISON
            Stats base = currMonster.getBaseStats();
            if((base.getHP() - base.getMaxHP() * 0.0625)<=0){
                base.setHP(0);
                currMonster.setIsDead(true);
                System.out.println(currMonster.getNama()+" poisoned to death");
            } 
            else{
                base.setHP(base.getHP() - base.getMaxHP() * 0.0625);
            }
            currMonster.setBaseStats(base); 

        } else {
            // do nothing
        }
    }

    // method-method lain -> damage, effect, dan seterusnya
}
