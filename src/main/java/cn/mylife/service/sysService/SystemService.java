package cn.mylife.service.sysService;


/**
 * @author yangjie
 * @date 2018/9/14 8:55
 */
public class SystemService {

    /**
     * 获取Key加载信息
     */
    public static boolean printKeyLoadMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n===============================================================================\r\n");
        sb.append(
                "    ////////////////////////////////////////////////////////////////////\n" +
                "    //                          _ooOoo_                               //\n" +
                "    //                         o8888888o                              //\n" +
                "    //                         88\" . \"88                              //\n" +
                "    //                         (| ^_^ |)                              //\n" +
                "    //                         O\\  =  /O                              //\n" +
                "    //                      ____/`---'\\____                           //\n" +
                "    //                    .'  \\\\|     |//  `.                         //\n" +
                "    //                   /  \\\\|||  :  |||//  \\                        //\n" +
                "    //                  /  _||||| -:- |||||-  \\                       //\n" +
                "    //                  |   | \\\\\\  -  /// |   |                       //\n" +
                "    //                  | \\_|  ''\\---/''  |   |                       //\n" +
                "    //                  \\  .-\\__  `-`  ___/-. /                       //\n" +
                "    //                ___`. .'  /--.--\\  `. . ___                     //\n" +
                "    //              .\"\" '<  `.___\\_<|>_/___.'  >'\"\".                  //\n" +
                "    //            | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |                 //\n" +
                "    //            \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /                 //\n" +
                "    //      ========`-.____`-.___\\_____/___.-`____.-'========         //\n" +
                "    //                           `=---='                              //\n" +
                "    //      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //\n" +
                "    //            佛祖保佑       永不宕机     永无BUG                     //\n" +
                "    ////////////////////////////////////////////////////////////////////");
        sb.append("\n");
        sb.append(
                "                                            _ _      \r\n" +
                "                                           (_|_)     \n" +
                "                   _   _  __ _ _ __   __ _  _ _  ___ \n" +
                "                  | | | |/ _` | '_ \\ / _` || | |/ _ \\\n" +
                "                  | |_| | (_| | | | | (_| || | |  __/\n" +
                "                   \\__, |\\__,_|_| |_|\\__, || |_|\\___|\n" +
                "                    __/ |             __/ |/ |       \n" +
                "                   |___/             |___/__/");
         sb.append("\r\n===============================================================================\r\n");
        System.out.println(sb.toString());
        return true;
    }
}
