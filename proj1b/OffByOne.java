public class OffByOne implements CharacterComparator {

    public  OffByOne (){}

    @Override
    public boolean equalChars(char x, char y){
        if (Math.abs(Character.getNumericValue(x)-Character.getNumericValue(y) )==1){
            return true;
        }
        return false;
    }

    public static void main(String[] agrs){
        CharacterComparator offBy = new OffByOne();
        System.out.println(offBy.equalChars('a', 'b'));
        System.out.println(offBy.equalChars('r', 'q'));

    }
}
