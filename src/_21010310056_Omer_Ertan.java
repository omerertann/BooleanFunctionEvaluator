import java.util.*;
import java.io.*;

class _21010310056_Omer_Ertan_MantikFonksiyonu {
    char symbol;
    List<Integer> minterms;
    List<Integer> maxterms;
    Set<Integer> presentRows;
    int numVariables;
    char[] variables;

    _21010310056_Omer_Ertan_MantikFonksiyonu(char symbol, int numVariables, char[] variables) {
        this.symbol = symbol;
        this.numVariables = numVariables;
        this.variables = variables.clone();
        this.minterms = new ArrayList<>();
        this.maxterms = new ArrayList<>();
        this.presentRows = new HashSet<>();
    }

    void addRow(int index, int value) {
        presentRows.add(index);
        if (value == 1) {
            minterms.add(index);
        } else {
            maxterms.add(index);
        }
    }

    void sortTerms() {
        Collections.sort(minterms);
        Collections.sort(maxterms);
    }

    private String getMintermAlgebra(int index) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numVariables; i++) {
            int bit = (index >> (numVariables - 1 - i)) & 1;
            sb.append(variables[i]);
            if (bit == 0) sb.append("'");
        }
        return sb.toString();
    }

    private String getMaxtermAlgebra(int index) {
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < numVariables; i++) {
            if (i > 0) sb.append(" + ");
            int bit = (index >> (numVariables - 1 - i)) & 1;
            sb.append(variables[i]);
            if (bit == 1) sb.append("'");
        }
        sb.append(")");
        return sb.toString();
    }

    String getMintermLine() {
        StringBuilder sb = new StringBuilder();
        sb.append(symbol).append(" = Σ(");
        for (int i = 0; i < minterms.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(minterms.get(i));
        }
        sb.append(") = ");
        for (int i = 0; i < minterms.size(); i++) {
            if (i > 0) sb.append(" + ");
            sb.append("m").append(minterms.get(i));
        }
        sb.append(" = ");
        for (int i = 0; i < minterms.size(); i++) {
            if (i > 0) sb.append(" + ");
            sb.append(getMintermAlgebra(minterms.get(i)));
        }
        return sb.toString();
    }

    String getMaxtermLine() {
        StringBuilder sb = new StringBuilder();
        sb.append(symbol).append(" = ∏(");
        for (int i = 0; i < maxterms.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(maxterms.get(i));
        }
        sb.append(") = ");
        for (int i = 0; i < maxterms.size(); i++) {
            if (i > 0) sb.append(" . ");
            sb.append("M").append(maxterms.get(i));
        }
        sb.append(" = ");
        for (int i = 0; i < maxterms.size(); i++) {
            if (i > 0) sb.append(".");
            sb.append(getMaxtermAlgebra(maxterms.get(i)));
        }
        return sb.toString();
    }

    int evaluate(int[] values) {
        int index = 0;
        for (int i = 0; i < numVariables; i++) {
            index = (index << 1) | values[i];
        }
        if (!presentRows.contains(index)) return -1;
        if (minterms.contains(index)) return 1;
        return 0;
    }

    String getVariableListTurkish() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numVariables; i++) {
            if (i == numVariables - 1 && i > 0) {
                sb.append(" ve ");
            } else if (i > 0) {
                sb.append(", ");
            }
            sb.append(variables[i]);
        }
        return sb.toString();
    }
}

class _21010310056_Omer_Ertan_DogrulukTablosuAyirici {
    static List<_21010310056_Omer_Ertan_MantikFonksiyonu> parse(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String headerLine = reader.readLine();
        String[] headerParts = headerLine.split("\\|");

        String[] varTokens = headerParts[0].trim().split("\\s+");
        int numVars = varTokens.length;
        char[] variables = new char[numVars];
        for (int i = 0; i < numVars; i++) {
            variables[i] = varTokens[i].charAt(0);
        }

        List<_21010310056_Omer_Ertan_MantikFonksiyonu> functions = new ArrayList<>();
        String[] funcTokens = headerParts[1].trim().split("\\s+");
        for (String token : funcTokens) {
            if (!token.isEmpty()) {
                functions.add(new _21010310056_Omer_Ertan_MantikFonksiyonu(token.charAt(0), numVars, variables));
            }
        }

        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\|");
            String[] varValues = parts[0].trim().split("\\s+");

            int index = 0;
            for (String v : varValues) {
                index = (index << 1) | Integer.parseInt(v.trim());
            }

            String[] funcValues = parts[1].trim().split("\\s+");
            for (int i = 0; i < functions.size(); i++) {
                functions.get(i).addRow(index, Integer.parseInt(funcValues[i].trim()));
            }
        }
        reader.close();

        for (_21010310056_Omer_Ertan_MantikFonksiyonu f : functions) {
            f.sortTerms();
        }

        return functions;
    }
}

public class _21010310056_Omer_Ertan {
    public static void main(String[] args) {
        try {
            List<_21010310056_Omer_Ertan_MantikFonksiyonu> functions = _21010310056_Omer_Ertan_DogrulukTablosuAyirici.parse("dogruluk_tablosu.txt");
            System.out.println("dogruluk_tablosu.txt dosyası okundu.");
            System.out.println();

            Scanner scanner = new Scanner(System.in);

            for (_21010310056_Omer_Ertan_MantikFonksiyonu func : functions) {
                System.out.println(func.getMintermLine());
                System.out.println();
                System.out.println(func.getMaxtermLine());
                System.out.println();

                System.out.println("Lütfen " + func.symbol + " fonksiyonu için "
                        + func.getVariableListTurkish()
                        + " değişkenlerinin değerlerini arada boşluk bırakarak giriniz:");

                int[] values = new int[func.numVariables];
                boolean gecerliGiris = false;
                while (!gecerliGiris) {
                    try {
                        for (int i = 0; i < func.numVariables; i++) {
                            values[i] = scanner.nextInt();
                            if (values[i] != 0 && values[i] != 1) throw new InputMismatchException();
                        }
                        gecerliGiris = true;
                    } catch (InputMismatchException e) {
                        scanner.nextLine();
                        System.out.println("Geçersiz giriş. Lütfen sadece 0 veya 1 değerlerini arada boşluk bırakarak giriniz:");
                    }
                }

                int result = func.evaluate(values);
                if (result == -1) {
                    System.out.println("Girilen değerlere göre " + func.symbol
                            + " fonksiyonunun değeri belirsizdir.");
                } else {
                    System.out.println("Girilen değerlere göre " + func.symbol
                            + " fonksiyonunun değeri " + result + " dir.");
                }
                System.out.println();
            }

            scanner.close();
        } catch (IOException e) {
            System.out.println("Dosya okunamadı: " + e.getMessage());
        }
    }
}
