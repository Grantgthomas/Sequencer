/**
 * Created by Grant on 2/7/2017.
 */
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Scanner;
public class Sequencer {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String dna = ""; // DNA string
        String line = null; // Input String
        String atcg = "atcg"; //String to check for valid chars
        int codonStart= 0;
        int codonEnd = 0;
        int position;

        String Eyes = "";
        String Hair = "";
        String Tounge = "";
        String codon= "";

        //string which will be total dna sequence
        System.out.println("Input lowercase DNA fragments one line at a time. End with a blank line.");


        while (s.hasNextLine()) {
            line = s.nextLine();// take input from the user
            line = line.trim().toLowerCase();

            while (line != "") {

                codonStart = 0;
                codonEnd = 0;
                boolean charvalid = true;
                // check to make sure atcg are the only chars in line
                for (int a = 0; a < line.length(); a++) {
                    if (atcg.indexOf(line.charAt(a)) == -1)
                        charvalid = false;
                }
                //if an invalid character is detected don't set the dna to the line.
                if (charvalid == false) {
                    System.out.println("DNA is invalid");
                }
                //{
                if (dna != "") {
                    //find longest overlap
                    for (int i = 0; i <= line.length(); i++) {
                        if (dna.endsWith(line.substring(0, line.length() - i))) {
                            line = line.substring(line.length() - i);
                            break;
                        }
                    }
                    dna += line;
                } else dna = line;
                //find the start of a codon
                line = "";
            //    System.out.println("Input DNA: " + dna);
            }



            //     System.out.println("DNA does not contain a gene end codon");
            if (dna.contains("atg")) {

                codonStart = dna.indexOf("atg");
                // loop to form the actual codon
                for (int h = dna.indexOf("atg")+3; h <= dna.length()-3;h+=3 ) {
                    if(dna.substring(codonStart+3,h+3).endsWith("tag")) {
                        codonEnd = h ;
                        break;
                    }
                    else if(dna.substring(codonStart+3,h+3).endsWith("tga")) {
                        codonEnd = h ;
                        break;
                    }
                    else if(dna.substring(codonStart+3,h+3).endsWith("taa")) {
                        codonEnd = h ;
                        break;
                    }
                }
                codon = dna.substring(codonStart, codonEnd);

                if (codon.length() % 3 == 0 ) {
                    //if the codon is the correct length
                    //System.out.println("GENE: " + codon);
                    //System.out.println("Start Codon Position: " + codonStart + "\nEnd Codon Position: " + codonEnd);
                    //Start decoding the Gene
                    if(codon.length()>=30){
                        for (int c = 0; c < 3; c++) {
                            switch (c) {
                                case 0:
                                    position = 20;
                                    switch (codon.charAt(position)) {
                                        case 'a':

                                            Eyes = "blue";
                                            break;
                                        case 't':
                                            Eyes = "green";
                                            break;
                                        case 'g':
                                            Eyes = "brown";
                                            break;
                                        case 'c':
                                            Eyes = "brown";
                                            break;
                                    }
                                case 1:
                                    position = 18;
                                    switch (codon.charAt(position)) {
                                        case 'a':
                                            Hair = "black";
                                            break;
                                        case 't':
                                            Hair = "blond";
                                            break;
                                        case 'g':
                                            Hair = "red";
                                            break;
                                        case 'c':
                                            Hair = "brown";
                                            break;
                                    }
                                case 2:
                                    position = 8;
                                    switch (codon.charAt(position)) {
                                        case 'a':
                                            Tounge = "yes";
                                            break;
                                        case 't':
                                        case 'g':
                                        case 'c':
                                            Tounge = "no";
                                            break;
                                    }
                            }
                        }
                    }

                }/* else if (codon.length() < 9) {
                    //there isn't a multiple of 3 characters between the begining and ending of the codon
                    System.out.println("Start codon position: " + codonStart + "\nEnd codon position: " + codonEnd);
                    System.out.println("Gene: " + codon);
                    System.out.println("The gene is not long enough to continue.");
                } else if (codon.length() == 0) {
                    //There isn't a valid anding sequence to the codon
                    System.out.println("Input DNA: " + dna);
                    System.out.println("Start codon position: " + codonStart + "\nEnd codon position: " + codonEnd);
                }*/
                //if it isn't the correct length
            }

        }
        if(dna.indexOf("atg")==-1) {
            System.out.println("Input DNA: " + dna);
            System.out.println("DNA does not contain a gene start codon");
        }else if(dna.contains("atg")){
            System.out.println("Input DNA: " + dna);
            if(codonEnd!=0) {
                System.out.println("Start codon position: " + codonStart + "\nEnd codon position: " + codonEnd);
                System.out.println("Gene: " + codon);
            }
            if(dna.length() >=30)
            System.out.println("\nAnalysis Results\n"+"\nEye color: " + Eyes+ "\nHair color: " + Hair + "\nCan roll tongue? "+ Tounge);
            else {
                if(dna.length()%3!=0){
                    System.out.println("DNA does not contain a gene end codon");
                }else if(dna.length()<30 && codonStart!=codonEnd){
                    System.out.println("The gene is not long enough to continue.");
                }else if((dna.endsWith("tag"))==false||(dna.endsWith("tga"))==false||(dna.endsWith("taa"))==false){
                    System.out.println("DNA does not contain a gene end codon");
                }
            }
        }
    }
}