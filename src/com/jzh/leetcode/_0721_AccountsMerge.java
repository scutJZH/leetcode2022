package com.jzh.leetcode;

import java.util.*;

/**
 * 给定一个列表 accounts，每个元素 accounts[i]是一个字符串列表，其中第一个元素 accounts[i][0]是名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
 * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
 * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是 按字符 ASCII 顺序排列 的邮箱地址。账户本身可以以 任意顺序 返回。
 *
 * 思路：使用并查集。
 *  解1：用并查集来联通所有邮箱：这种方式不好，需要在并查集里维护太多东西
 *  解2：用并查集来联通账号
 *      分析：① index可以对应到人名，比起人名会重复，index不会；② 可以通过外部map的方式将邮箱对应到index，如果发现邮箱对应过了，则合并账号
 */
public class _0721_AccountsMerge {
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("David","Avid0@m.co","David0@m.co","David1@m.co");
        List<String> list2 = Arrays.asList("David","Gvid3@m.co","David3@m.co","David4@m.co");
        List<String> list3 = Arrays.asList("David","David4@m.co","David5@m.co");
        List<String> list4 = Arrays.asList("Mary", "mary@mail.com");

        List<List<String>> accounts = Arrays.asList(list1, list2, list3, list4);

        accountsMerge2(accounts);
    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        if (accounts == null || accounts.size() == 0) {
            return Collections.emptyList();
        }

        UnionFind1 unionFind = new UnionFind1();
        for (List<String> accountsOfOne : accounts) {
            String name = accountsOfOne.get(0);
            for (int j = 1; j < accountsOfOne.size(); j++) {
                unionFind.add(name, accountsOfOne.get(j));
                if (j > 1) {
                    unionFind.union(accountsOfOne.get(j - 1), accountsOfOne.get(j));
                }
            }
        }

        List<List<String>> result = new ArrayList<>();
        for (PersonAccount personAccount : unionFind.accountMap.values()) {
            List<String> list = new ArrayList<>();
            list.add(personAccount.personName);

            List<String> accountList = new ArrayList<>(personAccount.accounts);
            accountList.sort(String::compareTo);
            list.addAll(accountList);

            result.add(list);
        }

        return result;
    }

    public static class UnionFind1 {
        Map<String, String> parentMap;
        Map<String, PersonAccount> accountMap;

        public UnionFind1() {
            parentMap = new HashMap<>();
            accountMap = new HashMap<>();
        }

        public void add(String name, String account) {
            if (!parentMap.containsKey(account)) {
                parentMap.put(account, account);
                PersonAccount personAccount = new PersonAccount(name);
                personAccount.accounts.add(account);
                accountMap.put(account, personAccount);
            }
        }

        public String findParent(String account) {
            List<String> list = new ArrayList<>();

            while (!account.equals(parentMap.get(account))) {
                list.add(account);
                account = parentMap.get(account);
            }

            // 降层
            for (String s : list) {
                parentMap.put(s, account);
            }

            return account;
        }

        public void union(String account1, String account2) {
            String a1Parent = findParent(account1);
            String a2Parent = findParent(account2);

            if (a1Parent.equals(a2Parent)) {
                return;
            }

            parentMap.put(a2Parent, a1Parent);

            accountMap.get(a1Parent).accounts.addAll(accountMap.get(a2Parent).accounts);
            accountMap.remove(a2Parent);
        }

        public boolean isSameSet(String account1, String account2) {
            return findParent(account1).equals(findParent(account2));
        }
    }

    public static class PersonAccount {
        String personName;
        Set<String> accounts;

        public PersonAccount(String name) {
            personName = name;
            accounts = new HashSet<>();
        }
    }

    public static List<List<String>> accountsMerge2(List<List<String>> accounts) {
        if (accounts == null || accounts.size() == 0) {
            return Collections.emptyList();
        }

        Map<String, Integer> map = new HashMap<>(); // 储存邮箱和账号的对应关系，并记录邮箱是否出现过
        UnionFind2 unionFind2 = new UnionFind2(accounts.size());

        // 联通账号
        for (int i = 0; i < accounts.size(); i++) {
            List<String> accountsOfOne = accounts.get(i);
            for (int j = 1; j < accountsOfOne.size(); j++) {
                String account = accountsOfOne.get(j);
                if (map.containsKey(account)) {
                    unionFind2.union(i, map.get(account));
                } else {
                    map.put(account, i);
                }
            }
        }

        // 将所有邮箱的对应账号改为账号的祖先
        Map<Integer, List<String>> accountsMap = new HashMap<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int parent = unionFind2.find(entry.getValue());
            List<String> list = accountsMap.getOrDefault(parent, new ArrayList<>());
            list.add(entry.getKey());
            accountsMap.put(parent, list);
        }

        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : accountsMap.entrySet()) {
            List<String> list = new ArrayList<>(entry.getValue());
            list.sort(String::compareTo);
            list.add(0, accounts.get(entry.getKey()).get(0));

            result.add(list);
        }

        return result;
    }

    public static class UnionFind2 {
        private int[] parents;

        public UnionFind2(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public int find(int i) {
            if (parents[i] != i) {
                // 压缩
                parents[i] = find(parents[i]);
            }

            return parents[i];
        }

        public void union(int i, int j) {
            if (find(i) == find(j)) {
                return;
            }

            parents[find(j)] = find(i);
        }
    }

}
