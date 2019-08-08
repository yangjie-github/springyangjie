package cn.mylife.utils.randomcode;

import java.util.Random;

/**
 * @author yangjie
 * 2018/11/18 11:41
 */
public class RandomNumCode {

    /**
     * 生成随机六位数验证码
     */
    public static String getRandomNumCode(int number){
        String codeNum = "";
        int [] numbers = {0,1,2,3,4,5,6,7,8,9};
        Random random = new Random();
        //目的是产生足够随机的数，避免产生的数字重复率高的问题
        for (int i = 0; i < number; i++) {
            int next = random.nextInt(10000);
            codeNum += numbers[next%10];
        }
        return codeNum;
    }
}
