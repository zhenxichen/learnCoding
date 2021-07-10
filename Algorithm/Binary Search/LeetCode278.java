public class LeetCode278 {
    /* LeetCode 278 第一个错误的版本 */

    /**
     * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。
     * 由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
     * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。
     * 实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     */
    public static class Solution extends VersionControl {
        public int firstBadVersion(int n) {
            int left = 1, right = n;
            int ans = 0;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);     // 防止数据溢出
                if (isBadVersion(mid)) {
                    ans = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return ans;
        }
    }

    public static class VersionControl {
        private int bad = 4;

        public boolean isBadVersion(int version) {
            return version >= bad;
        }

        public void setBad(int bad) {
            this.bad = bad;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int ans1 = s.firstBadVersion(5);
        s.setBad(1702766719);
        int ans2 = s.firstBadVersion(2126753390);
        System.out.println(ans1);
        System.out.println(ans2);
    }
}
