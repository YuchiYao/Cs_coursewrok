public class Palindrome {
      /*Convert string into a list of char*/
    public static Deque <Character> wordToDeque(String word){
        Deque char_list = new LinkedListDeque();
        for (int i=0;i<word.length();i++){
            char_list.addLast(word.charAt(i));
        }
        return  char_list;
    }

    /*Check Palindrome or not*/
    public static boolean isPalindrome(String word){
        Deque char_list = wordToDeque(word);
        while(char_list.size() >= 2){
            if(char_list.removeFirst()!=char_list.removeLast()){  // Compare at begin and end
                return false;
            }
        }
        return true;                //Only one char is left, that must be Palindrome
    }

    public static boolean isPalindrome(String word,CharacterComparator cc){
        Deque <Character> char_list = wordToDeque(word);
        //cc = new OffByOne();
        char a,b;
        while(char_list.size() >= 2){
            a = char_list.removeFirst();
            b = char_list.removeLast();
            if (a != b || cc.equalChars(a, b)) {  // Compare at begin and end
                return false;
            }
        }
        return true;
    }

}
