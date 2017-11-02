public class OffByN implements CharacterComparator {

    private int N;

    public OffByN(int n){
        N = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (Math.abs(Character.getNumericValue(x)-Character.getNumericValue(y) )==5){
            return true;
        }
        return false;
    }

    public static void main(String[] agrs){
        CharacterComparator offBy5 = new OffByN(5);

       System.out.println(offBy5.equalChars('a', 'f')) ;
       System.out.println(offBy5.equalChars('f', 'h')) ;

    }
}
