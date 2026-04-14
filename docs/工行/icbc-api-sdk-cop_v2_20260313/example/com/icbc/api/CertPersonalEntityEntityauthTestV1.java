package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.CertPersonalEntityEntityauthRequestV1;
import com.icbc.api.response.CertPersonalEntityEntityauthResponseV1;

/**
 * @Author:kfzx-lihh
 * @Description:测试类，身份识别API接口调用
 * @Date:2019/6/6 15:27
 * @Modified By
 */
public class CertPersonalEntityEntityauthTestV1 {
    /*public static void main(String[] args) {
        String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCTBL1u89Ka0tsMNEqbYPbJjxTn8Xkr8tIHwJZ9odHWkcfTmnOU877cYl+UhdhkfcFEKuvEhX2isPGdaIrqzkm9RBh+K1PQwyViGAhkFiUXDNcp/MdPzGyEjhwd+adKwR3CJfZZoWhZITT0O7fq71ufcSLvCrc6jzOPIDzxLheVall79ZHgVOCDjpTI8JUsj/0kAmHzhRIJ3lUGWyv4mTipwyAT9lzmvXhMcMnzStJ7Jzn1le7VXAd3ERvEz/PKEaXtPJ3kd1Grt0Q2EAns6ozvUg7spEjSBL7K8G77nwuOGWvVqfYQ2o36f5UTczpDaq47zrFVq6tFOIh21LBiYEThAgMBAAECggEAMKeb7bFBnL11gtd3bW6snc39dDllCZdlzOz+oPtBz8oVIMF3yO7V1me0DBozoCZCJMYzmqk3B/WZMKfdeZHls35qwK6kIW3D3G/prv3pLnSwbJPcfls/+qpxQbZSmBnLOGqJ2eXh+8VSmhV9a4Hlh6HQlt2r//ROcuqPYJ4JbgfvW/XIOCzakodAFeGLR2MXa5w+M7/Wc4Ipy1R5Cn10ZElJdAq8Hn8maAzQnQMNfkElZwX+7P8kyv/ueeeTeR+4HHhP5Xd0XLAJ4WVgw/jvzpRyBFdy+Kalvc0G0utIR8+9yUXlQ9yojJFVTzVukq+RHgPQg9PFLtYW9BKMkXgbsQKBgQDhCskBxEgr6DsdjoqpVYHSq8IyTD46+ton1lR6yE6cfuabPqdqVhV1rcANPNI7sCHjdQJUOABJiOIXn5v3soTx2C3dnWVUAck33e5t0fXwlpN5d2lEok6ZYhk1n29S/fDv5FLP860hYIxWD1Hggcd/AqhTPqXAVDgIpAThi9id/QKBgQCnPjrOqsj7Sq0+3b9LF8ScSquxK5QaAJ712SnBT/EbjQJK0QiNHRl/03jFMIUwXHDVd6wYo0JYCBJFDZuZyVndPONz2rlx9Y9nP112RZedo4/lIdXV4ZQl8nsUrSS3zn2dHA9d/kcLTgV+0WQr1CSrCDnxXOgXRGNyu1dNJN8ltQKBgAcygRs9W9GLq9hWJrkjsvM56wwQxCy+uRttD2ad4WW/6UAt0Mzl0+nflLTXrSGbkHnl1B09bUTsy85VnHdfjkDt2QmtbwAwzm6q+mUoweTf6/eGiidXyYu+R3AfmT/eP0Zs1vkvoenfXJ2j/8peXDMDsr520nZEO0aLeFx+TH9BAoGBAIgEp6Vv5zfhe1d2197F/OS0IkFc1P305VPEBB6x2sFyhJu+uHq0YQnCPyX5GUnE1Qv4YaJEHXAlWF6K+7MuvISfwcl26X4Snkf1lBjd7rqJzT0osJZb+baHKK0fSI6pRSZjLOhgJv29veBvgk3XIt9rOFziF+zcdRlZ3l3ikA5FAoGAfI7mPDQby6/BPFMBG3A7UuL29X7Gb4RfOpqS49YsNuCDA0YYirHyOhn49Ai3s9YuegWLOSlWdm2Goz03YuNW7fbVI+WrNc4EeIyRAkd81PgF9gxTNKq+LVMzaD7bGf8an8F95fvgZi4xNuLHukjGW7Exr9ND8W4y8fv8Grw8RkA=";
        String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
        DefaultIcbcClient client = new DefaultIcbcClient("10000000000004094755", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        CertPersonalEntityEntityauthRequestV1 request = new CertPersonalEntityEntityauthRequestV1();
        //6月版apip测试环境，行外网关
        request.setServiceUrl("http://ip:port/api/cert/personal/entity/entityauth/V1");
        CertPersonalEntityEntityauthRequestV1.CertPersonalProofProofcheckRequestV1Biz bizContent = new CertPersonalEntityEntityauthRequestV1.CertPersonalProofProofcheckRequestV1Biz();
        StringBuffer sb = new StringBuffer("");
        String h1 = "/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0a\n" +
                "HBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIy\n" +
                "MjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAEsAPsDASIA\n" +
                "AhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQA\n" +
                "AAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3\n" +
                "ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWm\n" +
                "p6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEA\n" +
                "AwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSEx\n" +
                "BhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElK\n" +
                "U1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3\n" +
                "uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD0PxMc\n" +
                "PCfY/wBK5tpPeuj8UDmE/WuXarqfEetg1ekhTIab5hNNJ4ptZHZZDvMNLuJPWo6cKB2JVdhTgxz1\n" +
                "pi0+mSxxY460m9s9aaaKYrDtx9aXcelNHFLikAm8jvSbyaCOKbQNIXec0hkPrSEU0ikVYDIcVGXP\n" +
                "Y04ikIGKCkkM8xvWkLn1NJ3pG6UFWAyEdzSeaQO9NxSd6Vx8qJfMyOv4UwuaTHFIaQKIhkI6GkDn\n" +
                "PJpppuaLl2RMZqQT9c1C1KoINFxcqJhcEj5elHmMetMVdtO607hyomjY5GK9Egz5EfH8IrzyIHt2\n" +
                "r0SH/Up9BW9DqePmf2TO8UfdhPua5ZjXVeKP9VF/vH+Vcq3WlV+I0wX8JEeOaNvNOHNLisjtuN20\n" +
                "oFOxmjFAriiloxil71QhRRSY5px6UCAHjpTeSaTcRT1JOeKQDSKTaM08sOpqPfk/KM0DFao2NSMJ\n" +
                "CPuGoGilbkYp8rGmurELc8UxjmpFhIUeZ9761E7CNiMUrMuLT2EwaQ0eZnrwKTcD3pF2DimZ5pxF\n" +
                "MbikUhc0wtRnNITSKsISaaRmlJpQaQxEjLuFyB9aeBg/SlVTjPanAZoFca3tS9qTOTijJzxQhE8R\n" +
                "IwPWvRoeYEP+yK84hHzD2r0aAAQIB02iumh1PHzNfCUfFC5giP8Atf0rkyOa67xP/wAe8R/2/wCl\n" +
                "coetKr8RWB/hIYq1IBxQMU4VmdYdO1NIGafmkwDQA3FAXnNLgZpS2KLgJigcmgHJpGkVM0AKygDN\n" +
                "ML7eh5qrLeA5CmoPMljxIzcdmxVRi2ZVK0Ka95mlbqZbpd4wo6j1q+0cXRcA1h/bpnK+X85P92tK\n" +
                "K1urhB5rbfp1rXlUdzz6uMcn7ossiQ53sPwpFubUrgk/gKmTTVDcru+tWkskXoopc6MHVnIypLiB\n" +
                "BnBHsRVaaWGUMwC5rdmtEZcFAazptMRgdqlSfSlzJmlLETgc614N2CBgU9bhWU9qfd6PNDGxT5u+\n" +
                "KxizwnDAihxTR6VPGxdkzcWUbRzS53VkRXXOM1oxzBgAKwkrHowakrxJGGKbTielNqC0GM04Djig\n" +
                "HipEFMTGhsDBpSzdBT2RTyRSKuTmgkaEOBmn7RTiMClA4oBsIhh69GhH7lPpXnSH5h6Zr0aHiFPp\n" +
                "XRQ6nkZp9kpeJx/okZ/2/wChrkz1rrPEzgWKZP8Ay0H8jXImWPPBoq/EPA/whysM80/cMcUw4AUn\n" +
                "IDcg460DAOVNZnWOaRV+8ab5o61FIN3UVUkQg5XP50my4wTLplAFNEi96pl9o5NN80c5alc09mWp\n" +
                "bpU6fnWbd6gFBK8+tOmuo1Q7jmuZ1K6BJZJBjPQ1pCPM7IxxFSNCnzSLE+tKrbCTnsa09HgutVGz\n" +
                "ewhzyev5VmaJop1SdZZVYxDv616RZWkVpCEiQKAOgFb1KiguVHziUqkuaQun6VDZQhUTnux6mtHa\n" +
                "qio1c460oJHeuZyubKFhwC5zil+lNGTTwKB2GsoIqJkAFWNp60xlBphYpPEGzWNqekQ3anja46MK\n" +
                "3nGKruM/ShSsHKeZanbXGmTbJASp6OOhpbC/w2Hau51GwhvIWSRAwNeZavZT6VenhvKJ4atFaasa\n" +
                "0cROhK+6OuiuY5RkGpg6nvXKaXdkuu5hg+9dCjBzkHiuaSs7H0dGaqwU0WwwqVG5qqDjvzTvMIHB\n" +
                "xSLcS4c96cMEVQFyQeTmn/bfQYouifZyLZAHNJnI61U+0Mxznin+fkYzii4cjLUR+cfWvRYgfKTn\n" +
                "sK83hcEgCvSYv9SnPaumh1PGzRfCZvipgNOU/wC2K4lpExnPNdb4vP8AxKifR1NeetIfWoxDtI6c\n" +
                "rp81G/maDXsigKD8o5AJpTf8ZHH41lljjk0wvWHOz01Qiawvz3NRSXufuZFZpbNJuOMUuZlKjEuG\n" +
                "4yelIZeKrKc8ZpGJU4PBo5mX7NFXUbgLFx1qhpOmSatehSD5QOWNM1WVhIFGOvTNdt4XsRbaejEf\n" +
                "M/JNawm4q6PBzX3qqh2NvT7WO1hVEUADgcVfBqFeKdms3I89KxYWpAKgRsgVMGppgSCnCmBs07Nb\n" +
                "REx3amN060uaaxqmCRBIOtVG+tWpDwapMeTWMtykhklY+p6bDewPHKoIbr61qyNxVaRsiknYGrnm\n" +
                "F/aPpOoCNtxj6oT3FdBp9wJIQSc1Y8TWS3doXA+eLlaw9FnDQ4zyOOaqo7q56uVSak6fQ6MOF/8A\n" +
                "r0jNnmq+8U7fxzWNz2uUf09qVscVGHycYpG60rhYlDYGM04c9Kg7jFTLTuJotQMVOOtenwnMKc9q\n" +
                "8tjGWA716hF/qk+ldeH6ng5wvhMjxh/yB5PZl/nXnBJzXpHi8f8AEll+o/mK83PWoxPxHTk/8B+o\n" +
                "hOaSl7Ulcp64UhoopjEPSmsc5zTiKYelIaMOYedq8SHkFv616jp6BLWMAdBXmKAHXIgfWvU7IYto\n" +
                "/pW3RHzOOf8AtEiwKeMVE7BFLMcAda5W/wDEcguGSDG0HGRUWOQ7RMGpVFcFF4nuoRyoZj/eq/Y+\n" +
                "LJGlCXEQAJ6irVgtc7MClqlbajDcKCrDPpVwOCK2ViRaQjIoJqGa4WJSxPApjEkWqEowaoX/AIot\n" +
                "rZivJPtWNJ4wgbPykfWsZW6FKLN6Q8GoG5FYy+JYHYZPBrUjmWaMMhBBHBqbDKN9FvX8K4izHkan\n" +
                "NBkEBjj25r0GVdwIrz4gr4kuQB/FQ17rOvANrERsbynIp2T2NMX7vvS1gfT2Hg45p+6oRT6dxNEg\n" +
                "NTIcdKhWpk6VSM2ieI/MK9QiP7pPpXl8WNwr0+I/uk+grsw3U8DOPs/MzPFgzos3OMY/mK81PWvT\n" +
                "fFQzodx+H8xXmRHNRiviRvk38B+oNknkAHpSClxRXKewNope9HemNDcU1l4p9IemKRRh3I8nU4JM\n" +
                "Yy2Ca9SsTmzjI7qK831OHdFuA5U5Fd94buBdaHbv1IXB9sVqndHz2ZUuWrz9ye/hee1MaNt3dazb\n" +
                "fw9AuSVye9b7pxVeW8htELSuqqO5o6nnWuUv7Dt9mBGKpTaGsZ3AU288eaZY43LKwboQvBplr450\n" +
                "jUW8tGdWP95apwurhy2ZZtYHhlBBOK6KCQ7axo5kkcGMgqehFbNunyiohLUbHvKQCaxtRmkkVlXv\n" +
                "WrcKQtZMuC+DVSk0COam0aS5fJzzUZ8I8ZLNmt+61vTNLXN1cKuDjaOTVMeOtBlcRrM+T/sU4x0u\n" +
                "O1zmLjQ7i3c7QcCtfQ3njbyZc4xxW2t5Zaiha3lSQegNRR26pLuAxUtBaxIRlWJ9K4KIedrdzIOm\n" +
                "84xXaXczwWtzI2AqISD+Fcdo8REbSN1Y5om/dPQyym5Vubsa2McUuKB0pRWB9EJinAUnQ06mDHgc\n" +
                "8VMuAODUIHSng00yGixH1HrXqMX+qX6V5ZGfmHpXqcR/dJ9K7MN1Pn85XwfMo+KBnQ7n/d/rXmB6\n" +
                "16j4lGdEuh/sV5e3BNTit0a5L/Cl6iUFW2g449aSlycYycVyntDR1pRSUtA7BUbHGakqKT7hoGhI\n" +
                "7U6gHjR/mA4HrXUeD4nt9KaJwQVlYYNZnhy0LEyeprsLeERg7R1Oa0ijxMzqOUuToiR1LIawdT0Z\n" +
                "7vO6Q47CulApTGG7U3G55Wq2PLvFXh67uLS2+zwqzwgqQOMisTw14av4b1pJ4SqgEYbvXsktsrAj\n" +
                "FV0sFByBWyk+Wxlya3MLQ9MntVZZWBBOVA7V1MK7VANNjhCAVMB6VkopM13ILn7tY11DI0cnlthy\n" +
                "DtPoa2bkcVVCjuKbV2B5lq/hu/axUqBNcK5YknrWJofhrUE1eKS5gKxqdxz3r2OS2V+cU1LRR2Fa\n" +
                "J2VjOUbu5xX9j3EV8Z7f93k/dHFdNaxyGJTIPmxzWr9mX0ppjCnpWTjrc1u7WMDX4j/Y9yAOWTFc\n" +
                "xFC9pEiSLjcBiu8vUU2z5XPFcDfea+puTnaG4HoKmotD18rm1Jx7lzPSlzTF+4KWsT3LDs5pwqMU\n" +
                "9aBMlU0+o0qQUEkkf3h9a9Ui/wBUn0rytByK9Ti/1S/Su3C7M+fzr7HzK3iQZ0O7/wCuZry8jNeo\n" +
                "+IB/xI7odf3R6/SvLm60sVui8l/hS9RtJRSiuU9tIbS4oHXijvQMT3phG4HtT6MUDOg8N7Ta4H3l\n" +
                "JBrpITmMGuL0G7FvfNC33Zen1rsoOEwK3WqPn8xg41W+5bWn1GpqSqR5whGaTAFOpjGhgNJwacKZ\n" +
                "tJOaeKS3AhnFVCQKuykY61nyocnBoe4yZORUqp3qrCx6HrV1fu00KwhHFV5BVljxVaVhTZRUuP8A\n" +
                "VMPauP1WMLc5A5c1185HlnNcdfy+ffHHRPlrKbsj08ri3Vv0RCBgClp22jaT2rA9+40U4elAFOxg\n" +
                "g5oE2OWpBTFHGafQSyaPg16jEP3SfSvLoz8wr1KE/uU+grtwuzPn86+x8yDXx/xJrv8A65N/KvLC\n" +
                "Oteq64M6Rdj/AKZN/KvK24oxW6KyT+HL1GUUH2GKK5D3RKKKMUAFFFFAxjsUZZF4KHIIrvbGbzba\n" +
                "N/7yg1wjAEYrb8OXknmyWzuWUcrntVxlbQ4Mxo+0pcy6HXoalzVZD0NShq0TPnh5PFQzSCOMue1K\n" +
                "WzUcpBQg9KG7gYF/4mlt5glvbrMCOTuxU8GviWAO6eW3dSc4ol023aTcIwD7Cqk+kxsdwJFZ++ac";
        String h2 = "0drCX/iRIIyQpdh0UVX0rxM98zCa3MQHIJbNL/YsJO5gT9aI9Jtw+SuPbNHvE80TctZRON6ng1eV\n" +
                "sCs+ALHEEUYFWRJWidiSZ3qpI1OeXiqkkvJobKRR1W5NvZMy/ePAzXNxrtGSASeau6xO0t0It3yJ\n" +
                "zj3qoo4rGcr6H0mBo+zop9WPLE+1N/GiioOsKMUtFIYLUo6VGtSCgTJYzgivUoj+5T6CvK1616pD\n" +
                "/qU/3RXbhNmfP539j5jNZ50u6H/TJv5V5Yy7icfWvU9ZQPplwSASsbEH04NeWliucdxinit0LJfg\n" +
                "l6kJ60uKMGjkVyHvjTS5GDxThjndn2xTKAQUZpythSuBn1ptAAfWreiyCPU1yQNykVQmkCCsW91w\n" +
                "6dfW7Ds4JA7CnFNvQxxUowoyc3oeuxPx1qYMMVj2d9HcQxyxsGR1BBq6JSarmPliyz4qq8oJxups\n" +
                "rsUwDzWFeadezhvLumQHqMUua7sioRUnZuxqTanaQnaZQWHYGqZ1y2Y7e3rXPNoV2D80/wBeKX+w\n" +
                "ZNhb7Yc/7tdEYNnZHD0rbm5JrlqnyqCTU0V7BP8AdcZ9K5eXRZ9u5brJ91qsbW9t2zFJu+vWiUGh\n" +
                "Tw0LaM7QykdDxUiXBPeuZtbi/wBgEwx75rShmb+KuZzs7HG4WZqPPkYzVOSYDJB6VA9yBnNULy9j\n" +
                "igcs2M/KOe5pp30RUFqrlGSUzXEkh7tUmeKpxuVO1sHPQ1ZUggVElZ6n10bcq5dh+aTrSCkqSrD8\n" +
                "0CmmlHSgLD1qRSM+1MU9acKCSZSCFAABHUjvXqMH+oj/AN0V5anBr1K3ObeM/wCyK7cJ1PAzraHz\n" +
                "DVv+QZc/9c2/lXlRHzYxk16vqgzp1wB/zzb+VeUk4YHHSni+hGSfBIjPBINJ1px2kcAg5puPSuQ9\n" +
                "9B2xTadTDQMWgsFHNNZsLgDntW1pWkA7Zrpck8qh7VUIOTsjKvXhRjzTMuHTpbkiVgViHIz1Nec+\n" +
                "IrjztXnKnKo20fhXs+qsILZ9qjheK8IvHMlzK/q5P613QpKCPlMbjp4mXZI7rwT4kCQjT52G4f6s\n" +
                "n+Vd/DdB8c14FBIYpFZSQwOQRXpujauz28ZdieBXHiafK+ZCoz5lZneIwYCn1l2l4siDmtCNt3Xp\n" +
                "WMJXNGrEVxGWB21izG8jYhUBWuiIyeKjaJSDmtU30ZUajic6huGGGSneU2fmFbDKuOKqzgBSelJy\n" +
                "l1Y5VWym2ABVeSUIDRNKFzzWNe6gqKxLAAe9Y/EySzNegZOenfNcpf62bvUUjRv3UR/M1S1bWmmB\n" +
                "igYhT1PrWLDIRMDnv1ruoUeXVnNVq9InoOnXC3CeWx2k/d+tXk3BijfeXrXOaRMyyqTytdjJa/aL\n" +
                "YTwr+8X7w9RV4ijfVHrZTj7fuqj9CsaQdaar7h7jrTga8+x9IL3oANANLQIWnoeKZT16imiWTKel\n" +
                "epWxH2WL/cFeWJ1FepWp/wBEh/3B/KuzCdTwM72h8yXUhnT5/wDcP8q8mavWdR/48J/9w/yryZ6r\n" +
                "FdCMk+GQwmkzikLCmqWlfZEpc+1cZ797bjt3NMaQbsAEn0FaEGjs/NzJtH91eta9tZW8ICxoMevW\n" +
                "toUJS3OCvmVGlotWZen6Y7TrLcJgDlVrpVx07CoCNqlsdDT4SWBJrrp01BHz2Lxk8RK7MnXZP3Mg\n" +
                "xkbT/KvErlNs8gOchjXtmsp5ilApbvXk3iG1+z6g6lcZGRxitpbHAnqYozuBFdVpF1iNVLYGO9cw\n" +
                "rlFdQBhhg5HvmtGxn2gAHkVz1Y8yNacuVnd2motEQQeK6ey1WKSPlhu+tebQ3RAGTVtb2RG+VtpH\n" +
                "auB0mnodqmmtT00X8YXO4VQutZiAwGFcOdVuAnc/Ss+TVW3nOaqMZicoo7s6shH3hVafU0I+9muG\n" +
                "bVZB61Xn1mULgHBp+xkyfaxR0Op60kKE7ufSuMvtTmu3OWwvYVXuLhpm3MxJqvXVSoqBz1KrlsK2\n" +
                "QeSOaFwJAV6e9JToxlwPetzI6rSf9WmeRXoWkyL5AAORivPtIic4UeuR713WmI0MCgdc1q9UKErS\n" +
                "0E1Kw2s00IwepUd6zlfIzXSSg7QxGT0xWdPpscpLoSjH8q4K1C7vE+my/M1y8lZ/MzgeKXNEkFxb\n" +
                "n513L6rTA4PI6VyOLW57kZxmrxdybNOU81GpyakFIGTJ1HvXqVof9Dg/3B/KvLF616lZc2Fv/wBc\n" +
                "1/lXZhOp4GdbQ+ZNqPFhP/uN/KvJ0hmuWxGhK92A4r126AMD5/umuNkjWNiAABXRVp89jy8Djfq0\n" +
                "ZJLVmBb6ICc3EhP+yK1Io4oRsiUDHalmfB44NRF2Klj1pRpKIV8fVrfExksnLVLbOTHtOdx4qAJv\n" +
                "kPvVpDtRmPQcCtUjicriyvhRGG59asqNqgDrjms6MmWYnB2+h7VfVxyTTEVL6MSK2PvCvO/FViZk\n" +
                "LgfOnevSJ8v90Z9a5vVbfz48bf4u1NdjOXc8lI5xT4s7shtpHNauraaYZWeNeDkkVjEYNQ1YpSua\n" +
                "MV8BhXBz61aN2H+YPk1iA0ZYHisnTTLU2jdTUdnBPFKb23YEnGawdxxjNJn1zS9kh+0ZoXF6hyIx\n" +
                "Wezljkmm0AZPJxVqKRN7iHrSdKdg0lUAYzVqxh8y4AKniqvbpW3ocCkNI2fSmldiZ1GjQRo+4jou\n" +
                "Pxrp7QNGhYnj0xWPZxBIVP0IxW3A5AA2kDvmtGEUXG+aMY64zVXzCpIYDI6CpBJhC2eAe1V2cK24\n" +
                "gls8VLNE7D3YYDdiarPaQTHO3b7rxVoKHDAHCt+hqpueNjGc7h+tZygpbnRRxVSk7xdiq9rNEfkI\n" +
                "kHt1pqzfNtIwfer0Z2qMtnJ9KlktY7hcsMEdxXNPDL7J7WHzdS0qorR47c16dYEHT7b/AK5r/KvN\n" +
                "Gsprf50JkTuO4r0rTTu0u1PrEv8AKqw0XFtMxzepGpCEoO6L11/qH+hrhppCZmG449a7m6/1D/7p\n" +
                "rz+6wG3KeQeQK7UtD5u+pHKwQEMQR1pFZGjypznpiq0km+TseO9SRjf8o+X2pFE+1kHyd/0pLqQi\n" +
                "MIDjjrUsJyS54AqEjeNxOQO1Ax1sSiKCMk9TVwspU9R7GqqcsoPAqYlckdcUhoY0jBGVRlfXvWbN\n" +
                "GDGQSSpPX0rSkG1Dtzk9KpiNlUb+SCaaQpanJajar5pDcjB5FcRfW/k3BA4B5FelX8DEPLgZY8DH\n" +
                "auH16DaQ+O+M05K6IjozCxlgM4oIIPWlxz6UEcnBz71maDSOPelxRSkYbGQfegBuKMUtFACYoABz\n" +
                "k4oNGOKAExk12Gj2y/2eg6ORk1ycSb5lX1IrvtNhxEF25AwBVREzYso1MKqSTgYHHSrpZVjTDH+7\n" +
                "VGzZ1nnjJ+QD5c1dCAoAQCevFMpD8hVK+tIuXkKsCML36UkSgxFj1HanSKWVSDjFIY9WAb7vHWmH\n" +
                "/SAXYAOOmO4oGVcbjzjgGpSNrDHB7cUDKiZ8xwR09asxnGD+dMlQbvMJ5PUCnxDHXpSYJ2Llq+ZB\n" +
                "uHGcfWu5tiBaxAf3RXAoNp3DnB4ru7IhrKE+qCiKCUm1Yu3f+of6GvO5wPMcDrmvQrs4gk+lecXe\n" +
                "BMcE/wCFWtjnZTYBJMhunrV23J2F+9UHUspPeq811dWsieWMoeoxmkyzcyVwACc9qDgHCqVrmzr8\n" +
                "6S8wHjqM1KfEMitzCTk/lU8xVjdDeWPm9OppY5AE5OD1rGk1+3+TKnJ65HSpoNWtpS25sg/pSuh2\n" +
                "Zrhwytk+lR3B8uMkrx61TF5bFQFlU+hBpWunnTCt0BJPuOlWmSxl0geMryNgrjdbsWktj7jIrsml\n" +
                "WSI7xhyOlZdxZCeFgDg9hVIhnlxypII6cUnIrS1mxa0vG+X5Sf1rNrNqzsWnfUSl7UUvPUUihMel\n" +
                "LtIIBGD70lLuYkZY8dOaBAwKkqab260uTnNJigEW9NhM99GoBODmvQ7GFkiTIAOM1zfhiw2kzyDl\n" +
                "h8ua7QMvlKRjp1q0rIi92RTxCPEowy5AIHpV2NsAAAAfXtUAVQgVWJB65pjTQQlknYhtuQx6YoLR\n" +
                "bJVcr1x0wetNmMh2sgxg8qaxZfElnApRSWx3HNZk3iq4eNkjTGf4j1qHJF2OqkJK7y3P8qI7iNST\n" +
                "JKCcdSa4xb++uWGZCB27U3Zcu+Tu6881PP5DsjtVvomBVSrdqkgbCDd3rB0qGSP535z+lbjN+7AH\n" +
                "FO4i4soHTBrurA5sICD1QVwEIGRjGDXfWH/HhB/uCqiTIu3WTbv9K88m+aQgHnsK9Euv9Q/0Necz\n" +
                "qVumOckduwq+hk9yrJEyhgDg1GYiwGTkCrDhmBLHI6UwKVHy9BxQMqi3XczYz7YpWtV2rlQcegqz\n" +
                "kIhZuvYCmJKejKFz3zSKM64sYXYbUHvQulx7CACMjIrQ2oQQDlsZ5p0aEgbiee3tSaC5jPpMbxEJ\n" +
                "KVxWpbwGC0ijRwSF59/rTjHmPBBHPJoAIY4YFRwKLDvcaZYVkC43ODzjsaaxSPccnnnNOlEYI2qB\n" +
                "IOfrTJc+QN65bPSqIZzmu2cd0jgNluuT61w7qY5CpHIOMV6jcWqtBuDK31NcLr9n9nud6qNrdwO9\n" +
                "OSurii7Oxj9qSilrI1EpcDFLgY75pKAAnPWp7G2NzdxxgZyefpUB55wPwrpfC1o3mm4wR2Bx2pxV\n" +
                "2S3Y6extkTaOFQDtV7O3bH5eVPoKj8tHQbclh2zU7EDnOMcDmtbkpClFVAcZIPHNZ2s2rahYyogK\n" +
                "yKAyn6VoIjrKN5wmOeKcWEL9SU6A+lSxnAxWGeZDn1xV9LFE/hBHXmtDULZ4p22JtVucgcVW+Z8D\n" +
                "nAFCgh3JgiqFCoAcZq1HEHjzsU5PHamwgYRWIJI5A6gVLAgEeSCeaTQ0yxArKegA9KvqMr7VViCl\n" +
                "Qy444q0pBQYOM1DLRPb4IXBxg132n/8AIPg/3BXAwoqsQxz9K7vTjnT4Mf3acSZGldYMDj2Nee3O\n" +
                "POYAjfn0r0G6/wBS59q89mP709hmr6GLIeVBU9aiYbSWJxVgn5snv3qFlzuXG4YpFIgfJjyQuccH\n" +
                "NRx7d3JLelOKsSVOOen0qJ1EZGRz6inYLk6Lhy3fGMVIinadpGR71X2nOCxGecVKMKcA4I60AMmu\n" +
                "dsojPAP61EgkRR8owx49qfdW4li+UfPng1FFJI6tHMApHA560rWHe5JKzK65BOByRUM0jNgFueKc\n" +
                "xZD5RLYI6mlaAOqsfvY5PtTRNxAoOQoVs8gVzHiO3Z7MnHzg5x6V0qkiTGORyDVK7iFxbOWGSc4F\n" +
                "Ul0E+55qDxRU97Abe7kQjAzkVXrF6Gqegp+tGaO1IKAJI4zLIqAck44r0HSLcW1vHGvyjHP071yX\n" +
                "h6zNzqAfGVTn8a7pIo0BLnGAcc1cVoS3qTqw3ZQjjlTSri4zhiMdyO9KQPIBQDbgHPrTBNtZSqcs\n" +
                "OfQVQFgKWC5Y4FMkki2yAsDnjb60k07KEZRhiOQKpzzQ2266nJ3EfKPSkBHeB1j2FgT/AAjuKoxq\n" +
                "C/zDA9KkSY3SCXqW5HtTk+VwjfNnnjrVIYoRgARwQcA1awm5RuO4jJ4pU4X5eQeoNIwPmAkduKhj\n" +
                "RZBQL8pNSKwztzio0GyMHtQp+fcDn2qGWi4jFWz+dd7pZ/4lkHf5f6157AS5OTzmu80k/wDErgwe\n" +
                "x/macRSNu5H7hvpXn08eJmC8eleiyqGQg9xWHJo9m7lijZz/AHqtGL0OOLMx2nnFABVjgV1w0SxA\n" +
                "/wBW3P8AtU9dFsevln/vqiwrnEuHXnaBzjpUbRFxuKiu9Oj2RA/dfrTRotjz+59+pphc4FtysvGR\n" +
                "2qXymzuruxomn8fuAcepqRdHsVGBAtAXODSMsrY4xVe4sxMhGCGBzkV6IujWCjAt1p40ex6/Z1oA\n" +
                "8wU790T/AH/WpG3qgRwTngH2r0h9D01yC1rGSOc4p7aRYMm020ZHuKQjywoyjJP0FVH+ZXUZUg85\n" +
                "r13+yLDGPsseP90Uh0bTsbfskWP90U7hqfPPiO1K7JtpHOCa5/HNfTl54c0i7hMU9jC6ehUVnDwR\n" +
                "4dH/ADC4PyocU3cam0fOuDzxQBzX0WPBXh3/AKBcH/fNSJ4M8Pq4I0u34PHyCp5F3HzvseWeHLAw\n" +
                "6fvZMbuWatlY1XAdMjjnHUe9epxabZooVLaNRjGAop5sLU/8sI/++RTuGp5eXIcKqfL7elSPF5aj\n" +
                "C8j0r0z7Ba/88I/++RTvsdvn/Up/3yKdw1PLbh0tYmkn+UEZFczJJLqE5clhEOAvtXuslhaSjElv\n" +
                "Gw91Bpq6bZr0tohj0UVL1Ku0eORW7RDBGCB6VZjs2eRT0bH5V66LG1z/AKiP/vkUv2K2B4gT/vkV\n" +
                "TYlc8kRHBIx14pTbT+ZGCuOehFesiytu0KD/AICKcbSA9Yl49qmw02eTnzWG1ASM9Knjt2VzleR0\n" +
                "xXqAs7f/AJ4p+VO+yQf88l/KlYrmZ5fEjhvlHfBrt9Lf/iWw/Q/zNbItLf8A55J+VSrEiAKqgAdg\n" +
                "KLA3c//Z";
        sb.append(h1).append(h2);
        String h = sb.toString();
        bizContent.setChannel("2");
        bizContent.setPhotoData(sb.toString());
        bizContent.setReqId("123876545676545");
        bizContent.setName("元涛涛");
        bizContent.setIdCode("140424199604171615");
        request.setBizContent(bizContent);
        CertPersonalEntityEntityauthResponseV1 response;
        try {
            //测试时，避免apip识别成重放，请修改msgId,每次跑，递加1比如
            response = client.execute(request, "urcnl24ciutr62");
            System.out.println(response.getResult() + response.getReturnCode() + response.getReturnMsg());
            System.out.println("MsgId:" + response.getMsgId() + ";ReturnCode:" + response.getReturnCode() + ";ReturnMsg:" + response.getReturnMsg());
            if (response.isSuccess()) {
                // 业务成功处理
                String content = response.getResult();
                System.out.println("MsgId:" + response.getMsgId() + ";ReturnCode:" + response.getReturnCode() + ";ReturnMsg:" + response.getReturnMsg());
                System.out.println("content:" + content);
                //Assert.assertEquals(response.getReturnMsg(), "success");
            } else {
                // 失败
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

    //如需使用Juit进行测试，请将如下打开。
    //
    public void testUseJunit() {
        String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCTBL1u89Ka0tsMNEqbYPbJjxTn8Xkr8tIHwJZ9odHWkcfTmnOU877cYl+UhdhkfcFEKuvEhX2isPGdaIrqzkm9RBh+K1PQwyViGAhkFiUXDNcp/MdPzGyEjhwd+adKwR3CJfZZoWhZITT0O7fq71ufcSLvCrc6jzOPIDzxLheVall79ZHgVOCDjpTI8JUsj/0kAmHzhRIJ3lUGWyv4mTipwyAT9lzmvXhMcMnzStJ7Jzn1le7VXAd3ERvEz/PKEaXtPJ3kd1Grt0Q2EAns6ozvUg7spEjSBL7K8G77nwuOGWvVqfYQ2o36f5UTczpDaq47zrFVq6tFOIh21LBiYEThAgMBAAECggEAMKeb7bFBnL11gtd3bW6snc39dDllCZdlzOz+oPtBz8oVIMF3yO7V1me0DBozoCZCJMYzmqk3B/WZMKfdeZHls35qwK6kIW3D3G/prv3pLnSwbJPcfls/+qpxQbZSmBnLOGqJ2eXh+8VSmhV9a4Hlh6HQlt2r//ROcuqPYJ4JbgfvW/XIOCzakodAFeGLR2MXa5w+M7/Wc4Ipy1R5Cn10ZElJdAq8Hn8maAzQnQMNfkElZwX+7P8kyv/ueeeTeR+4HHhP5Xd0XLAJ4WVgw/jvzpRyBFdy+Kalvc0G0utIR8+9yUXlQ9yojJFVTzVukq+RHgPQg9PFLtYW9BKMkXgbsQKBgQDhCskBxEgr6DsdjoqpVYHSq8IyTD46+ton1lR6yE6cfuabPqdqVhV1rcANPNI7sCHjdQJUOABJiOIXn5v3soTx2C3dnWVUAck33e5t0fXwlpN5d2lEok6ZYhk1n29S/fDv5FLP860hYIxWD1Hggcd/AqhTPqXAVDgIpAThi9id/QKBgQCnPjrOqsj7Sq0+3b9LF8ScSquxK5QaAJ712SnBT/EbjQJK0QiNHRl/03jFMIUwXHDVd6wYo0JYCBJFDZuZyVndPONz2rlx9Y9nP112RZedo4/lIdXV4ZQl8nsUrSS3zn2dHA9d/kcLTgV+0WQr1CSrCDnxXOgXRGNyu1dNJN8ltQKBgAcygRs9W9GLq9hWJrkjsvM56wwQxCy+uRttD2ad4WW/6UAt0Mzl0+nflLTXrSGbkHnl1B09bUTsy85VnHdfjkDt2QmtbwAwzm6q+mUoweTf6/eGiidXyYu+R3AfmT/eP0Zs1vkvoenfXJ2j/8peXDMDsr520nZEO0aLeFx+TH9BAoGBAIgEp6Vv5zfhe1d2197F/OS0IkFc1P305VPEBB6x2sFyhJu+uHq0YQnCPyX5GUnE1Qv4YaJEHXAlWF6K+7MuvISfwcl26X4Snkf1lBjd7rqJzT0osJZb+baHKK0fSI6pRSZjLOhgJv29veBvgk3XIt9rOFziF+zcdRlZ3l3ikA5FAoGAfI7mPDQby6/BPFMBG3A7UuL29X7Gb4RfOpqS49YsNuCDA0YYirHyOhn49Ai3s9YuegWLOSlWdm2Goz03YuNW7fbVI+WrNc4EeIyRAkd81PgF9gxTNKq+LVMzaD7bGf8an8F95fvgZi4xNuLHukjGW7Exr9ND8W4y8fv8Grw8RkA=";
        String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
        DefaultIcbcClient client = new DefaultIcbcClient("10000000000004094755", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        CertPersonalEntityEntityauthRequestV1 request = new CertPersonalEntityEntityauthRequestV1();
        //6月版apip测试环境，行外网关
        request.setServiceUrl("http://ip:port/api/cert/personal/entity/entityauth/V1");
        CertPersonalEntityEntityauthRequestV1.CertPersonalProofProofcheckRequestV1Biz bizContent = new CertPersonalEntityEntityauthRequestV1.CertPersonalProofProofcheckRequestV1Biz();
        StringBuffer sb = new StringBuffer("");
        String h1 = "/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0a\n" +
                "HBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIy\n" +
                "MjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAEsAPsDASIA\n" +
                "AhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQA\n" +
                "AAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3\n" +
                "ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWm\n" +
                "p6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEA\n" +
                "AwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSEx\n" +
                "BhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElK\n" +
                "U1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3\n" +
                "uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD0PxMc\n" +
                "PCfY/wBK5tpPeuj8UDmE/WuXarqfEetg1ekhTIab5hNNJ4ptZHZZDvMNLuJPWo6cKB2JVdhTgxz1\n" +
                "pi0+mSxxY460m9s9aaaKYrDtx9aXcelNHFLikAm8jvSbyaCOKbQNIXec0hkPrSEU0ikVYDIcVGXP\n" +
                "Y04ikIGKCkkM8xvWkLn1NJ3pG6UFWAyEdzSeaQO9NxSd6Vx8qJfMyOv4UwuaTHFIaQKIhkI6GkDn\n" +
                "PJpppuaLl2RMZqQT9c1C1KoINFxcqJhcEj5elHmMetMVdtO607hyomjY5GK9Egz5EfH8IrzyIHt2\n" +
                "r0SH/Up9BW9DqePmf2TO8UfdhPua5ZjXVeKP9VF/vH+Vcq3WlV+I0wX8JEeOaNvNOHNLisjtuN20\n" +
                "oFOxmjFAriiloxil71QhRRSY5px6UCAHjpTeSaTcRT1JOeKQDSKTaM08sOpqPfk/KM0DFao2NSMJ\n" +
                "CPuGoGilbkYp8rGmurELc8UxjmpFhIUeZ9761E7CNiMUrMuLT2EwaQ0eZnrwKTcD3pF2DimZ5pxF\n" +
                "MbikUhc0wtRnNITSKsISaaRmlJpQaQxEjLuFyB9aeBg/SlVTjPanAZoFca3tS9qTOTijJzxQhE8R\n" +
                "IwPWvRoeYEP+yK84hHzD2r0aAAQIB02iumh1PHzNfCUfFC5giP8Atf0rkyOa67xP/wAe8R/2/wCl\n" +
                "coetKr8RWB/hIYq1IBxQMU4VmdYdO1NIGafmkwDQA3FAXnNLgZpS2KLgJigcmgHJpGkVM0AKygDN\n" +
                "ML7eh5qrLeA5CmoPMljxIzcdmxVRi2ZVK0Ka95mlbqZbpd4wo6j1q+0cXRcA1h/bpnK+X85P92tK\n" +
                "K1urhB5rbfp1rXlUdzz6uMcn7ossiQ53sPwpFubUrgk/gKmTTVDcru+tWkskXoopc6MHVnIypLiB\n" +
                "BnBHsRVaaWGUMwC5rdmtEZcFAazptMRgdqlSfSlzJmlLETgc614N2CBgU9bhWU9qfd6PNDGxT5u+\n" +
                "KxizwnDAihxTR6VPGxdkzcWUbRzS53VkRXXOM1oxzBgAKwkrHowakrxJGGKbTielNqC0GM04Djig\n" +
                "HipEFMTGhsDBpSzdBT2RTyRSKuTmgkaEOBmn7RTiMClA4oBsIhh69GhH7lPpXnSH5h6Zr0aHiFPp\n" +
                "XRQ6nkZp9kpeJx/okZ/2/wChrkz1rrPEzgWKZP8Ay0H8jXImWPPBoq/EPA/whysM80/cMcUw4AUn\n" +
                "IDcg460DAOVNZnWOaRV+8ab5o61FIN3UVUkQg5XP50my4wTLplAFNEi96pl9o5NN80c5alc09mWp\n" +
                "bpU6fnWbd6gFBK8+tOmuo1Q7jmuZ1K6BJZJBjPQ1pCPM7IxxFSNCnzSLE+tKrbCTnsa09HgutVGz\n" +
                "ewhzyev5VmaJop1SdZZVYxDv616RZWkVpCEiQKAOgFb1KiguVHziUqkuaQun6VDZQhUTnux6mtHa\n" +
                "qio1c460oJHeuZyubKFhwC5zil+lNGTTwKB2GsoIqJkAFWNp60xlBphYpPEGzWNqekQ3anja46MK\n" +
                "3nGKruM/ShSsHKeZanbXGmTbJASp6OOhpbC/w2Hau51GwhvIWSRAwNeZavZT6VenhvKJ4atFaasa\n" +
                "0cROhK+6OuiuY5RkGpg6nvXKaXdkuu5hg+9dCjBzkHiuaSs7H0dGaqwU0WwwqVG5qqDjvzTvMIHB\n" +
                "xSLcS4c96cMEVQFyQeTmn/bfQYouifZyLZAHNJnI61U+0Mxznin+fkYzii4cjLUR+cfWvRYgfKTn\n" +
                "sK83hcEgCvSYv9SnPaumh1PGzRfCZvipgNOU/wC2K4lpExnPNdb4vP8AxKifR1NeetIfWoxDtI6c\n" +
                "rp81G/maDXsigKD8o5AJpTf8ZHH41lljjk0wvWHOz01Qiawvz3NRSXufuZFZpbNJuOMUuZlKjEuG\n" +
                "4yelIZeKrKc8ZpGJU4PBo5mX7NFXUbgLFx1qhpOmSatehSD5QOWNM1WVhIFGOvTNdt4XsRbaejEf\n" +
                "M/JNawm4q6PBzX3qqh2NvT7WO1hVEUADgcVfBqFeKdms3I89KxYWpAKgRsgVMGppgSCnCmBs07Nb\n" +
                "REx3amN060uaaxqmCRBIOtVG+tWpDwapMeTWMtykhklY+p6bDewPHKoIbr61qyNxVaRsiknYGrnm\n" +
                "F/aPpOoCNtxj6oT3FdBp9wJIQSc1Y8TWS3doXA+eLlaw9FnDQ4zyOOaqo7q56uVSak6fQ6MOF/8A\n" +
                "r0jNnmq+8U7fxzWNz2uUf09qVscVGHycYpG60rhYlDYGM04c9Kg7jFTLTuJotQMVOOtenwnMKc9q\n" +
                "8tjGWA716hF/qk+ldeH6ng5wvhMjxh/yB5PZl/nXnBJzXpHi8f8AEll+o/mK83PWoxPxHTk/8B+o\n" +
                "hOaSl7Ulcp64UhoopjEPSmsc5zTiKYelIaMOYedq8SHkFv616jp6BLWMAdBXmKAHXIgfWvU7IYto\n" +
                "/pW3RHzOOf8AtEiwKeMVE7BFLMcAda5W/wDEcguGSDG0HGRUWOQ7RMGpVFcFF4nuoRyoZj/eq/Y+\n" +
                "LJGlCXEQAJ6irVgtc7MClqlbajDcKCrDPpVwOCK2ViRaQjIoJqGa4WJSxPApjEkWqEowaoX/AIot\n" +
                "rZivJPtWNJ4wgbPykfWsZW6FKLN6Q8GoG5FYy+JYHYZPBrUjmWaMMhBBHBqbDKN9FvX8K4izHkan\n" +
                "NBkEBjj25r0GVdwIrz4gr4kuQB/FQ17rOvANrERsbynIp2T2NMX7vvS1gfT2Hg45p+6oRT6dxNEg\n" +
                "NTIcdKhWpk6VSM2ieI/MK9QiP7pPpXl8WNwr0+I/uk+grsw3U8DOPs/MzPFgzos3OMY/mK81PWvT\n" +
                "fFQzodx+H8xXmRHNRiviRvk38B+oNknkAHpSClxRXKewNope9HemNDcU1l4p9IemKRRh3I8nU4JM\n" +
                "Yy2Ca9SsTmzjI7qK831OHdFuA5U5Fd94buBdaHbv1IXB9sVqndHz2ZUuWrz9ye/hee1MaNt3dazb\n" +
                "fw9AuSVye9b7pxVeW8htELSuqqO5o6nnWuUv7Dt9mBGKpTaGsZ3AU288eaZY43LKwboQvBplr450\n" +
                "jUW8tGdWP95apwurhy2ZZtYHhlBBOK6KCQ7axo5kkcGMgqehFbNunyiohLUbHvKQCaxtRmkkVlXv\n" +
                "WrcKQtZMuC+DVSk0COam0aS5fJzzUZ8I8ZLNmt+61vTNLXN1cKuDjaOTVMeOtBlcRrM+T/sU4x0u\n" +
                "O1zmLjQ7i3c7QcCtfQ3njbyZc4xxW2t5Zaiha3lSQegNRR26pLuAxUtBaxIRlWJ9K4KIedrdzIOm\n" +
                "84xXaXczwWtzI2AqISD+Fcdo8REbSN1Y5om/dPQyym5Vubsa2McUuKB0pRWB9EJinAUnQ06mDHgc\n" +
                "8VMuAODUIHSng00yGixH1HrXqMX+qX6V5ZGfmHpXqcR/dJ9K7MN1Pn85XwfMo+KBnQ7n/d/rXmB6\n" +
                "16j4lGdEuh/sV5e3BNTit0a5L/Cl6iUFW2g449aSlycYycVyntDR1pRSUtA7BUbHGakqKT7hoGhI\n" +
                "7U6gHjR/mA4HrXUeD4nt9KaJwQVlYYNZnhy0LEyeprsLeERg7R1Oa0ijxMzqOUuToiR1LIawdT0Z\n" +
                "7vO6Q47CulApTGG7U3G55Wq2PLvFXh67uLS2+zwqzwgqQOMisTw14av4b1pJ4SqgEYbvXsktsrAj\n" +
                "FV0sFByBWyk+Wxlya3MLQ9MntVZZWBBOVA7V1MK7VANNjhCAVMB6VkopM13ILn7tY11DI0cnlthy\n" +
                "DtPoa2bkcVVCjuKbV2B5lq/hu/axUqBNcK5YknrWJofhrUE1eKS5gKxqdxz3r2OS2V+cU1LRR2Fa\n" +
                "J2VjOUbu5xX9j3EV8Z7f93k/dHFdNaxyGJTIPmxzWr9mX0ppjCnpWTjrc1u7WMDX4j/Y9yAOWTFc\n" +
                "xFC9pEiSLjcBiu8vUU2z5XPFcDfea+puTnaG4HoKmotD18rm1Jx7lzPSlzTF+4KWsT3LDs5pwqMU\n" +
                "9aBMlU0+o0qQUEkkf3h9a9Ui/wBUn0rytByK9Ti/1S/Su3C7M+fzr7HzK3iQZ0O7/wCuZry8jNeo\n" +
                "+IB/xI7odf3R6/SvLm60sVui8l/hS9RtJRSiuU9tIbS4oHXijvQMT3phG4HtT6MUDOg8N7Ta4H3l\n" +
                "JBrpITmMGuL0G7FvfNC33Zen1rsoOEwK3WqPn8xg41W+5bWn1GpqSqR5whGaTAFOpjGhgNJwacKZ\n" +
                "tJOaeKS3AhnFVCQKuykY61nyocnBoe4yZORUqp3qrCx6HrV1fu00KwhHFV5BVljxVaVhTZRUuP8A\n" +
                "VMPauP1WMLc5A5c1185HlnNcdfy+ffHHRPlrKbsj08ri3Vv0RCBgClp22jaT2rA9+40U4elAFOxg\n" +
                "g5oE2OWpBTFHGafQSyaPg16jEP3SfSvLoz8wr1KE/uU+grtwuzPn86+x8yDXx/xJrv8A65N/KvLC\n" +
                "Oteq64M6Rdj/AKZN/KvK24oxW6KyT+HL1GUUH2GKK5D3RKKKMUAFFFFAxjsUZZF4KHIIrvbGbzba\n" +
                "N/7yg1wjAEYrb8OXknmyWzuWUcrntVxlbQ4Mxo+0pcy6HXoalzVZD0NShq0TPnh5PFQzSCOMue1K\n" +
                "WzUcpBQg9KG7gYF/4mlt5glvbrMCOTuxU8GviWAO6eW3dSc4ol023aTcIwD7Cqk+kxsdwJFZ++ac";
        String h2 = "0drCX/iRIIyQpdh0UVX0rxM98zCa3MQHIJbNL/YsJO5gT9aI9Jtw+SuPbNHvE80TctZRON6ng1eV\n" +
                "sCs+ALHEEUYFWRJWidiSZ3qpI1OeXiqkkvJobKRR1W5NvZMy/ePAzXNxrtGSASeau6xO0t0It3yJ\n" +
                "zj3qoo4rGcr6H0mBo+zop9WPLE+1N/GiioOsKMUtFIYLUo6VGtSCgTJYzgivUoj+5T6CvK1616pD\n" +
                "/qU/3RXbhNmfP539j5jNZ50u6H/TJv5V5Yy7icfWvU9ZQPplwSASsbEH04NeWliucdxinit0LJfg\n" +
                "l6kJ60uKMGjkVyHvjTS5GDxThjndn2xTKAQUZpythSuBn1ptAAfWreiyCPU1yQNykVQmkCCsW91w\n" +
                "6dfW7Ds4JA7CnFNvQxxUowoyc3oeuxPx1qYMMVj2d9HcQxyxsGR1BBq6JSarmPliyz4qq8oJxups\n" +
                "rsUwDzWFeadezhvLumQHqMUua7sioRUnZuxqTanaQnaZQWHYGqZ1y2Y7e3rXPNoV2D80/wBeKX+w\n" +
                "ZNhb7Yc/7tdEYNnZHD0rbm5JrlqnyqCTU0V7BP8AdcZ9K5eXRZ9u5brJ91qsbW9t2zFJu+vWiUGh\n" +
                "Tw0LaM7QykdDxUiXBPeuZtbi/wBgEwx75rShmb+KuZzs7HG4WZqPPkYzVOSYDJB6VA9yBnNULy9j\n" +
                "igcs2M/KOe5pp30RUFqrlGSUzXEkh7tUmeKpxuVO1sHPQ1ZUggVElZ6n10bcq5dh+aTrSCkqSrD8\n" +
                "0CmmlHSgLD1qRSM+1MU9acKCSZSCFAABHUjvXqMH+oj/AN0V5anBr1K3ObeM/wCyK7cJ1PAzraHz\n" +
                "DVv+QZc/9c2/lXlRHzYxk16vqgzp1wB/zzb+VeUk4YHHSni+hGSfBIjPBINJ1px2kcAg5puPSuQ9\n" +
                "9B2xTadTDQMWgsFHNNZsLgDntW1pWkA7Zrpck8qh7VUIOTsjKvXhRjzTMuHTpbkiVgViHIz1Nec+\n" +
                "IrjztXnKnKo20fhXs+qsILZ9qjheK8IvHMlzK/q5P613QpKCPlMbjp4mXZI7rwT4kCQjT52G4f6s\n" +
                "n+Vd/DdB8c14FBIYpFZSQwOQRXpujauz28ZdieBXHiafK+ZCoz5lZneIwYCn1l2l4siDmtCNt3Xp\n" +
                "WMJXNGrEVxGWB21izG8jYhUBWuiIyeKjaJSDmtU30ZUajic6huGGGSneU2fmFbDKuOKqzgBSelJy\n" +
                "l1Y5VWym2ABVeSUIDRNKFzzWNe6gqKxLAAe9Y/EySzNegZOenfNcpf62bvUUjRv3UR/M1S1bWmmB\n" +
                "igYhT1PrWLDIRMDnv1ruoUeXVnNVq9InoOnXC3CeWx2k/d+tXk3BijfeXrXOaRMyyqTytdjJa/aL\n" +
                "YTwr+8X7w9RV4ijfVHrZTj7fuqj9CsaQdaar7h7jrTga8+x9IL3oANANLQIWnoeKZT16imiWTKel\n" +
                "epWxH2WL/cFeWJ1FepWp/wBEh/3B/KuzCdTwM72h8yXUhnT5/wDcP8q8mavWdR/48J/9w/yryZ6r\n" +
                "FdCMk+GQwmkzikLCmqWlfZEpc+1cZ797bjt3NMaQbsAEn0FaEGjs/NzJtH91eta9tZW8ICxoMevW\n" +
                "toUJS3OCvmVGlotWZen6Y7TrLcJgDlVrpVx07CoCNqlsdDT4SWBJrrp01BHz2Lxk8RK7MnXZP3Mg\n" +
                "xkbT/KvErlNs8gOchjXtmsp5ilApbvXk3iG1+z6g6lcZGRxitpbHAnqYozuBFdVpF1iNVLYGO9cw\n" +
                "rlFdQBhhg5HvmtGxn2gAHkVz1Y8yNacuVnd2motEQQeK6ey1WKSPlhu+tebQ3RAGTVtb2RG+VtpH\n" +
                "auB0mnodqmmtT00X8YXO4VQutZiAwGFcOdVuAnc/Ss+TVW3nOaqMZicoo7s6shH3hVafU0I+9muG\n" +
                "bVZB61Xn1mULgHBp+xkyfaxR0Op60kKE7ufSuMvtTmu3OWwvYVXuLhpm3MxJqvXVSoqBz1KrlsK2\n" +
                "QeSOaFwJAV6e9JToxlwPetzI6rSf9WmeRXoWkyL5AAORivPtIic4UeuR713WmI0MCgdc1q9UKErS\n" +
                "0E1Kw2s00IwepUd6zlfIzXSSg7QxGT0xWdPpscpLoSjH8q4K1C7vE+my/M1y8lZ/MzgeKXNEkFxb\n" +
                "n513L6rTA4PI6VyOLW57kZxmrxdybNOU81GpyakFIGTJ1HvXqVof9Dg/3B/KvLF616lZc2Fv/wBc\n" +
                "1/lXZhOp4GdbQ+ZNqPFhP/uN/KvJ0hmuWxGhK92A4r126AMD5/umuNkjWNiAABXRVp89jy8Djfq0\n" +
                "ZJLVmBb6ICc3EhP+yK1Io4oRsiUDHalmfB44NRF2Klj1pRpKIV8fVrfExksnLVLbOTHtOdx4qAJv\n" +
                "kPvVpDtRmPQcCtUjicriyvhRGG59asqNqgDrjms6MmWYnB2+h7VfVxyTTEVL6MSK2PvCvO/FViZk\n" +
                "LgfOnevSJ8v90Z9a5vVbfz48bf4u1NdjOXc8lI5xT4s7shtpHNauraaYZWeNeDkkVjEYNQ1YpSua\n" +
                "MV8BhXBz61aN2H+YPk1iA0ZYHisnTTLU2jdTUdnBPFKb23YEnGawdxxjNJn1zS9kh+0ZoXF6hyIx\n" +
                "Wezljkmm0AZPJxVqKRN7iHrSdKdg0lUAYzVqxh8y4AKniqvbpW3ocCkNI2fSmldiZ1GjQRo+4jou\n" +
                "Pxrp7QNGhYnj0xWPZxBIVP0IxW3A5AA2kDvmtGEUXG+aMY64zVXzCpIYDI6CpBJhC2eAe1V2cK24\n" +
                "gls8VLNE7D3YYDdiarPaQTHO3b7rxVoKHDAHCt+hqpueNjGc7h+tZygpbnRRxVSk7xdiq9rNEfkI\n" +
                "kHt1pqzfNtIwfer0Z2qMtnJ9KlktY7hcsMEdxXNPDL7J7WHzdS0qorR47c16dYEHT7b/AK5r/KvN\n" +
                "Gsprf50JkTuO4r0rTTu0u1PrEv8AKqw0XFtMxzepGpCEoO6L11/qH+hrhppCZmG449a7m6/1D/7p\n" +
                "rz+6wG3KeQeQK7UtD5u+pHKwQEMQR1pFZGjypznpiq0km+TseO9SRjf8o+X2pFE+1kHyd/0pLqQi\n" +
                "MIDjjrUsJyS54AqEjeNxOQO1Ax1sSiKCMk9TVwspU9R7GqqcsoPAqYlckdcUhoY0jBGVRlfXvWbN\n" +
                "GDGQSSpPX0rSkG1Dtzk9KpiNlUb+SCaaQpanJajar5pDcjB5FcRfW/k3BA4B5FelX8DEPLgZY8DH\n" +
                "auH16DaQ+O+M05K6IjozCxlgM4oIIPWlxz6UEcnBz71maDSOPelxRSkYbGQfegBuKMUtFACYoABz\n" +
                "k4oNGOKAExk12Gj2y/2eg6ORk1ycSb5lX1IrvtNhxEF25AwBVREzYso1MKqSTgYHHSrpZVjTDH+7\n" +
                "VGzZ1nnjJ+QD5c1dCAoAQCevFMpD8hVK+tIuXkKsCML36UkSgxFj1HanSKWVSDjFIY9WAb7vHWmH\n" +
                "/SAXYAOOmO4oGVcbjzjgGpSNrDHB7cUDKiZ8xwR09asxnGD+dMlQbvMJ5PUCnxDHXpSYJ2Llq+ZB\n" +
                "uHGcfWu5tiBaxAf3RXAoNp3DnB4ru7IhrKE+qCiKCUm1Yu3f+of6GvO5wPMcDrmvQrs4gk+lecXe\n" +
                "BMcE/wCFWtjnZTYBJMhunrV23J2F+9UHUspPeq811dWsieWMoeoxmkyzcyVwACc9qDgHCqVrmzr8\n" +
                "6S8wHjqM1KfEMitzCTk/lU8xVjdDeWPm9OppY5AE5OD1rGk1+3+TKnJ65HSpoNWtpS25sg/pSuh2\n" +
                "Zrhwytk+lR3B8uMkrx61TF5bFQFlU+hBpWunnTCt0BJPuOlWmSxl0geMryNgrjdbsWktj7jIrsml\n" +
                "WSI7xhyOlZdxZCeFgDg9hVIhnlxypII6cUnIrS1mxa0vG+X5Sf1rNrNqzsWnfUSl7UUvPUUihMel\n" +
                "LtIIBGD70lLuYkZY8dOaBAwKkqab260uTnNJigEW9NhM99GoBODmvQ7GFkiTIAOM1zfhiw2kzyDl\n" +
                "h8ua7QMvlKRjp1q0rIi92RTxCPEowy5AIHpV2NsAAAAfXtUAVQgVWJB65pjTQQlknYhtuQx6YoLR\n" +
                "bJVcr1x0wetNmMh2sgxg8qaxZfElnApRSWx3HNZk3iq4eNkjTGf4j1qHJF2OqkJK7y3P8qI7iNST\n" +
                "JKCcdSa4xb++uWGZCB27U3Zcu+Tu6881PP5DsjtVvomBVSrdqkgbCDd3rB0qGSP535z+lbjN+7AH\n" +
                "FO4i4soHTBrurA5sICD1QVwEIGRjGDXfWH/HhB/uCqiTIu3WTbv9K88m+aQgHnsK9Euv9Q/0Necz\n" +
                "qVumOckduwq+hk9yrJEyhgDg1GYiwGTkCrDhmBLHI6UwKVHy9BxQMqi3XczYz7YpWtV2rlQcegqz\n" +
                "kIhZuvYCmJKejKFz3zSKM64sYXYbUHvQulx7CACMjIrQ2oQQDlsZ5p0aEgbiee3tSaC5jPpMbxEJ\n" +
                "KVxWpbwGC0ijRwSF59/rTjHmPBBHPJoAIY4YFRwKLDvcaZYVkC43ODzjsaaxSPccnnnNOlEYI2qB\n" +
                "IOfrTJc+QN65bPSqIZzmu2cd0jgNluuT61w7qY5CpHIOMV6jcWqtBuDK31NcLr9n9nud6qNrdwO9\n" +
                "OSurii7Oxj9qSilrI1EpcDFLgY75pKAAnPWp7G2NzdxxgZyefpUB55wPwrpfC1o3mm4wR2Bx2pxV\n" +
                "2S3Y6extkTaOFQDtV7O3bH5eVPoKj8tHQbclh2zU7EDnOMcDmtbkpClFVAcZIPHNZ2s2rahYyogK\n" +
                "yKAyn6VoIjrKN5wmOeKcWEL9SU6A+lSxnAxWGeZDn1xV9LFE/hBHXmtDULZ4p22JtVucgcVW+Z8D\n" +
                "nAFCgh3JgiqFCoAcZq1HEHjzsU5PHamwgYRWIJI5A6gVLAgEeSCeaTQ0yxArKegA9KvqMr7VViCl\n" +
                "Qy444q0pBQYOM1DLRPb4IXBxg132n/8AIPg/3BXAwoqsQxz9K7vTjnT4Mf3acSZGldYMDj2Nee3O\n" +
                "POYAjfn0r0G6/wBS59q89mP709hmr6GLIeVBU9aiYbSWJxVgn5snv3qFlzuXG4YpFIgfJjyQuccH\n" +
                "NRx7d3JLelOKsSVOOen0qJ1EZGRz6inYLk6Lhy3fGMVIinadpGR71X2nOCxGecVKMKcA4I60AMmu\n" +
                "dsojPAP61EgkRR8owx49qfdW4li+UfPng1FFJI6tHMApHA560rWHe5JKzK65BOByRUM0jNgFueKc\n" +
                "xZD5RLYI6mlaAOqsfvY5PtTRNxAoOQoVs8gVzHiO3Z7MnHzg5x6V0qkiTGORyDVK7iFxbOWGSc4F\n" +
                "Ul0E+55qDxRU97Abe7kQjAzkVXrF6Gqegp+tGaO1IKAJI4zLIqAck44r0HSLcW1vHGvyjHP071yX\n" +
                "h6zNzqAfGVTn8a7pIo0BLnGAcc1cVoS3qTqw3ZQjjlTSri4zhiMdyO9KQPIBQDbgHPrTBNtZSqcs\n" +
                "OfQVQFgKWC5Y4FMkki2yAsDnjb60k07KEZRhiOQKpzzQ2266nJ3EfKPSkBHeB1j2FgT/AAjuKoxq\n" +
                "C/zDA9KkSY3SCXqW5HtTk+VwjfNnnjrVIYoRgARwQcA1awm5RuO4jJ4pU4X5eQeoNIwPmAkduKhj\n" +
                "RZBQL8pNSKwztzio0GyMHtQp+fcDn2qGWi4jFWz+dd7pZ/4lkHf5f6157AS5OTzmu80k/wDErgwe\n" +
                "x/macRSNu5H7hvpXn08eJmC8eleiyqGQg9xWHJo9m7lijZz/AHqtGL0OOLMx2nnFABVjgV1w0SxA\n" +
                "/wBW3P8AtU9dFsevln/vqiwrnEuHXnaBzjpUbRFxuKiu9Oj2RA/dfrTRotjz+59+pphc4FtysvGR\n" +
                "2qXymzuruxomn8fuAcepqRdHsVGBAtAXODSMsrY4xVe4sxMhGCGBzkV6IujWCjAt1p40ex6/Z1oA\n" +
                "8wU790T/AH/WpG3qgRwTngH2r0h9D01yC1rGSOc4p7aRYMm020ZHuKQjywoyjJP0FVH+ZXUZUg85\n" +
                "r13+yLDGPsseP90Uh0bTsbfskWP90U7hqfPPiO1K7JtpHOCa5/HNfTl54c0i7hMU9jC6ehUVnDwR\n" +
                "4dH/ADC4PyocU3cam0fOuDzxQBzX0WPBXh3/AKBcH/fNSJ4M8Pq4I0u34PHyCp5F3HzvseWeHLAw\n" +
                "6fvZMbuWatlY1XAdMjjnHUe9epxabZooVLaNRjGAop5sLU/8sI/++RTuGp5eXIcKqfL7elSPF5aj\n" +
                "C8j0r0z7Ba/88I/++RTvsdvn/Up/3yKdw1PLbh0tYmkn+UEZFczJJLqE5clhEOAvtXuslhaSjElv\n" +
                "Gw91Bpq6bZr0tohj0UVL1Ku0eORW7RDBGCB6VZjs2eRT0bH5V66LG1z/AKiP/vkUv2K2B4gT/vkV\n" +
                "TYlc8kRHBIx14pTbT+ZGCuOehFesiytu0KD/AICKcbSA9Yl49qmw02eTnzWG1ASM9Knjt2VzleR0\n" +
                "xXqAs7f/AJ4p+VO+yQf88l/KlYrmZ5fEjhvlHfBrt9Lf/iWw/Q/zNbItLf8A55J+VSrEiAKqgAdg\n" +
                "KLA3c//Z";
        sb.append(h1).append(h2);
        String h = sb.toString();
        bizContent.setChannel("2");
        bizContent.setPhotoData(sb.toString());
        bizContent.setReqId("123876545676545");
        bizContent.setName("元涛涛");
        bizContent.setIdCode("140424199604171615");
        bizContent.setScene("01");
        request.setBizContent(bizContent);
        CertPersonalEntityEntityauthResponseV1 response;
        try {
            //测试时，避免apip识别成重放，请修改msgId,每次跑，递加1比如
            response = client.execute(request, "urcnl24ciutr62");
            System.out.println(response.getResult() + response.getReturnCode() + response.getReturnMsg());
            System.out.println("MsgId:" + response.getMsgId() + ";ReturnCode:" + response.getReturnCode() + ";ReturnMsg:" + response.getReturnMsg());
            if (response.isSuccess()) {
                // 业务成功处理
                String content = response.getResult();
                System.out.println("MsgId:" + response.getMsgId() + ";ReturnCode:" + response.getReturnCode() + ";ReturnMsg:" + response.getReturnMsg());
                System.out.println("content:" + content);
                //Assert.assertEquals(response.getReturnMsg(), "success");
            } else {
                // 失败
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }*/
}
