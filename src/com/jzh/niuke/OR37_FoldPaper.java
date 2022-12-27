package com.jzh.niuke;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *  请把纸条竖着放在桌子上，然后从纸条的下边向上方对折，压出折痕后再展开。
 *  此时有1条折痕，突起的方向指向纸条的背面，这条折痕叫做“下”折痕 ；突起的方向指向纸条正面的折痕叫做“上”折痕。
 *  如果每次都从下边向上方对折，对折N次。请从上到下计算出所有折痕的方向。
 *
 * 给定折的次数n,请返回从上到下的折痕的数组，若为下折痕则对应元素为"down",若为上折痕则为"up".
 *
 * 思路：
 *  通过折纸发现，每一层的左孩子都为down，右孩子都为up
 *  使用中序遍历则为顺序打印
 */
public class OR37_FoldPaper {
    public static String[] foldPaper(int n) {
        // write code here
        List<String> list = inorder(n, true);
        String[] arr = new String[list.size()];
        return list.toArray(arr);
    }

    public static List<String> inorder(int n, boolean down) {
        if (n <= 0) {
            return Collections.emptyList();
        }

        // 左孩子
        List<String> list = new LinkedList<>(inorder(n - 1, true));
        // 自己
        list.add(down ? "down" : "up");
        // 右孩子
        list.addAll(inorder(n - 1, false));

        return list;
    }
}
