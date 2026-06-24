package miscellanious;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class GenerateSubsequences {
    static char[] workspace =null;

    public static void generateSubsequences(String s, Consumer<String> callback) {
        if (s==null || s.isEmpty()) return;
        workspace = new char[s.length()];
        backtrack(s, 0, 0, callback);
    }

    private static void backtrack(String s, int index, int writeLength, Consumer<String> callback) {
        // Base case: we have made a decision for every character
        if (index == s.length()) {
            if (writeLength > 0) { // Omit the empty string
                callback.accept(new String(workspace, 0, writeLength));
            }
            return;
        }

        workspace[writeLength] = s.charAt(index);

        // Choice 1: Include the current character
        backtrack(s, index + 1,  1+writeLength, callback);

        // Choice 2: Exclude the current character
        backtrack(s, index + 1, writeLength, callback);
    }

    public static void main(String[] args) {
        Set <String> set = new TreeSet<>(String::compareTo);
        String s="abcdefghijklmnopqrstu";
        long t0 = System.currentTimeMillis();
        generateSubsequences(s, set::add);
        long t1 = System.currentTimeMillis();
        System.out.println("Time taken(ms):"+(t1 - t0)+" size="+ set.size());
        System.out.println(set.stream().limit(10).collect(Collectors.toList()));
    }
}