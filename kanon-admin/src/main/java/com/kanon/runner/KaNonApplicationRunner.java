package com.kanon.runner;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 程序启动后通过ApplicationRunner处理一些事务
 *
 * @author wujiyue
 * @date 2018/6/6 16:07
 */
@Slf4j
@Component
public class KaNonApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments applicationArguments) {
        // 生成字符 http://patorjk.com/software/taag/#p=testall&f=Alpha&t=KANON
        log.info("KANON ENGINE STARTﾞ  \n" +
                "\n" +
                "                                                                   \n" +
                "       ,--.                        ,--.    ,----..            ,--. \n" +
                "   ,--/  /|   ,---,              ,--.'|   /   /   \\         ,--.'| \n" +
                ",---,': / '  '  .' \\         ,--,:  : |  /   .     :    ,--,:  : | \n" +
                ":   : '/ /  /  ;    '.    ,`--.'`|  ' : .   /   ;.  \\,`--.'`|  ' : \n" +
                "|   '   ,  :  :       \\   |   :  :  | |.   ;   /  ` ;|   :  :  | | \n" +
                "'   |  /   :  |   /\\   \\  :   |   \\ | :;   |  ; \\ ; |:   |   \\ | : \n" +
                "|   ;  ;   |  :  ' ;.   : |   : '  '; ||   :  | ; | '|   : '  '; | \n" +
                ":   '   \\  |  |  ;/  \\   \\'   ' ;.    ;.   |  ' ' ' :'   ' ;.    ; \n" +
                "|   |    ' '  :  | \\  \\ ,'|   | | \\   |'   ;  \\; /  ||   | | \\   | \n" +
                "'   : |.  \\|  |  '  '--'  '   : |  ; .' \\   \\  ',  / '   : |  ; .' \n" +
                "|   | '_\\.'|  :  :        |   | '`--'    ;   :    /  |   | '`--'   \n" +
                "'   : |    |  | ,'        '   : |         \\   \\ .'   '   : |       \n" +
                ";   |,'    `--''          ;   |.'          `---`     ;   |.'       \n" +
                "'---'                     '---'                      '---'         \n" +
                "                                                                   \n");
    }
}
