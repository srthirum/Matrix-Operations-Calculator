
//Creates a Matrix from the input file and performs operations on that matrix.

import java.io.*;
import java.util.Scanner;

public class Sparse {
   public static void main(String[] args) throws IOException {
      Scanner in = null;
      PrintWriter out = null;
      String line = null;
      String[] token = null;
      int i, n, numbLines = 0;

      if(args.length < 2){
         System.err.println("Usage: Sparse infile outfile");
         System.exit(1);
      }
      
      in = new Scanner(new File(args[0]));
      int L1, L2;

      line = in.nextLine()+" ";    // add extra space so split works right
      token = line.split("\\s+");  // split line around white space
      Matrix A = new Matrix(Integer.parseInt(token[0]));
      Matrix B = new Matrix(Integer.parseInt(token[0]));
      L1 = Integer.parseInt(token[1]) + 2;
      L2 = Integer.parseInt(token[2]) + L1 + 1;
      
      in = new Scanner(new File(args[0]));
      out = new PrintWriter(new FileWriter(args[1]));

      while( in.hasNextLine() ){
         numbLines++;
         line = in.nextLine()+" ";    // add extra space so split works right
         token = line.split("\\s+");  // split line around white space
         if(numbLines > 2 && numbLines <= L1) {
            A.changeEntry(Integer.parseInt(token[0]), Integer.parseInt(token[1]), Double.parseDouble(token[2]));
         }
         if(numbLines > (L1 + 1) && numbLines <= L2) {
            B.changeEntry(Integer.parseInt(token[0]), Integer.parseInt(token[1]), Double.parseDouble(token[2]));
         } 
      }

      out.println("A has " + A.getNNZ() + " non-zero entries:");
      out.println(A);
      
      out.println("B has " + B.getNNZ() + " non-zero entries:");
      out.println(B);

      out.println("(1.5)*A =");
      out.println(A.scalarMult(1.5));

      out.println("A+B =");
      out.println(A.add(B));
      
      out.println("A+A =");
      out.println(A.add(A));

      out.println("B-A =");
      out.println(B.sub(A));
      
      out.println("A-A =");
      out.println(A.sub(A));
      
      out.println("Transpose(A) =");
      out.println(A.transpose());
      
      out.println("A*B =");
      out.println(A.mult(B));
      
      out.println("B*B =");
      out.println(B.mult(B));
      
      in.close();
      out.close();
   }
}