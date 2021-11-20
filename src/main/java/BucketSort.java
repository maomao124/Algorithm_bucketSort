/**
 * Project name(项目名称)：算法_桶排序
 * Package(包名): PACKAGE_NAME
 * Class(类名): BucketSort
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2021/11/20
 * Time(创建时间)： 15:47
 * Version(版本): 1.0
 * Description(描述)： 桶排序（Bucket sort）或所谓的箱排序，是一个排序算法，工作的原理是将数组分到有限数量的桶里。
 * 每个桶再个别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排序），最后依次把各个桶中的记录列出来记得到有序序列。
 * 桶排序是鸽巢排序的一种归纳结果。当要被排序的数组内的数值是均匀分配的时候，桶排序使用线性时间（Θ(n)）。
 * 但桶排序并不是比较排序，他不受到O(n log n)下限的影响。
 * 实现逻辑
 * 设置一个定量的数组当作空桶子。
 * 寻访序列，并且把项目一个一个放到对应的桶子去。
 * 对每个不是空的桶子进行排序。
 * 从不是空的桶子里把项目再放回原来的序列中。
 * 平均时间复杂度：O(n + k)
 * 最佳时间复杂度：O(n + k)
 * 最差时间复杂度：O(n ^ 2)
 * 空间复杂度：O(n * k)
 * 稳定性：稳定
 */

public class BucketSort
{
    public static void sort(int[] arr)
    {
        int n = arr.length;
        // 使用数组来模拟链表（当然牺牲了部分的空间，但是操作却是简单了很多，稳定性也大大提高了）
        // 十个桶。建立一个二维数组，行向量的下标0—9代表了10个桶，每个行形成的一维数组则是桶的空间
        int[][] bask = new int[10][n];
        // 用来计算每个桶使用的容量
        int[] index = new int[10];
        // 计算最大的数有多少位。比如：5978，有4位
        int max = Integer.MIN_VALUE;
        for (int value : arr)
        {
            int k = (value + "").length();
            max = Math.max(max, k);
        }
        StringBuilder str;
        // 循环内将所有数据补齐，长度都为 max 。第一轮 i 代表个位，第二轮 i 代表十位。。。
        // 按照 个、十、百、千...的位置来计算
        // 第一轮将10以内的数据排好序，第二轮将100以内的数据排好序......
        for (int i = max - 1; i >= 0; i--)
        {
            System.out.println("第" + (max - i) + "轮补齐后的数据：");
            // 所有的数字都循环一遍
            for (int value : arr)
            {
                str = new StringBuilder();
                // 按照 max 将所有的数据补齐，位数不足的前面补零
                if (Integer.toString(value).length() < max)
                {
                    str.append("0".repeat(Math.max(0, max - Integer.toString(value).length())));
                }
                str.append(Integer.toString(value));
                System.out.printf("%5s", str.toString());
                // index[str.charAt(i) - '0']用于第二层循环计算每个桶使用的容量，第二层循环结束后会将index[str.charAt(i) - '0']都初始化为零
                // 第一轮取 str 的个位（str.charAt(i--)），放在第（str.charAt(i--) - '0'）个桶的第（index[str.charAt(i) - '0']++）个位置
                // 第二轮取 str 的十位（str.charAt(i--)），放在第（str.charAt(i--) - '0'）个桶的第（index[str.charAt(i) - '0']++）个位置
                // .......
                bask[str.charAt(i) - '0'][index[str.charAt(i) - '0']++] = value;
            }
            // 将桶内的数据重新放入数组内
            int pos = 0;
            for (int j = 0; j < 10; j++)
            {
                // 第j个桶内有index[j]个数据
                for (int k = 0; k < index[j]; k++)
                {
                    arr[pos++] = bask[j][k];
                }
            }
            System.out.println();
            System.out.println("第" + (max - i) + "轮index内的数据：");
            print(index);
            System.out.println("第" + (max - i) + "轮桶内的数据：");
            print(bask);
            System.out.println("第" + (max - i) + "轮结束后数组内的数据：");
            print(arr);
            System.out.println();
            // 将index[x]归零
            for (int x = 0; x < 10; x++) index[x] = 0;
        }
    }

    public static void print(int[][] array)
    {
        for (int[] a1 : array)
        {
            for (int a2 : a1)
            {
                System.out.print(a2 + " ");
            }
            System.out.println();
        }
    }

    private static void print(int[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
