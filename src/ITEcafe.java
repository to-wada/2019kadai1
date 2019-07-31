
import java.util.Calendar;
import java.util.Scanner;

public class ITEcafe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //商品の準備
        Item hotcoffee = new Item(1, "ホットコーヒー", 280);
        Item tea = new Item(2, "紅茶", 260);
        Item iceCoffee = new Item(3, "アイスコーヒー", 200);
        Item iceTea = new Item(4, "アイスティー", 260);
        Item shortCake = new Item(5, "ショートケーキ", 400);
        Item cheeseCake = new Item(6, "チーズケーキ", 400);
        Item chocolateCake = new Item(7, "チョコレートケーキ", 450);
        Item chocolateBananaParfait = new Item(8, "チョコレートバナナパフェ", 390);
        Item StrawberryParfait = new Item(9, "いちごパフェ", 390);
        Item meatPasta = new Item(10, "ミートパスタ", 650);
        Item mixPizza = new Item(11, "ミックスピザ", 700);
        Item croissant = new Item(12, "クロワッサン", 180);
        Item toastSandwich = new Item(13, "トーストサンド", 200);
        Item frenchToast = new Item(14, "フレンチトースト", 210);

        // 今回はItemを配列に入れて処理することにする　> 商品は14個なので14個のサイズの配列を作成しておく
        Item[] items = new Item[14];
        items[0] = hotcoffee;
        items[1] = tea;
        items[2] = iceCoffee;
        items[3] = iceTea;
        items[4] = shortCake;
        items[5] = cheeseCake;
        items[6] = chocolateCake;
        items[7] = chocolateBananaParfait;
        items[8] = StrawberryParfait;
        items[9] = meatPasta;
        items[10] = mixPizza;
        items[11] = croissant;
        items[12] = toastSandwich;
        items[13] = frenchToast;

        // 買い物かご
        ItemKago kago = new ItemKago();

        // 入力準備
        Scanner sc = new Scanner(System.in);

        //その他変数準備
        int goukeiKin = 0;  // 合計金額
        Calendar cal = Calendar.getInstance();  // 売上日時記録用
        int kaikeiNo = 1;   // 会計NO

        // レジ全体のループ
        while (true) {
            // １会計のループ
            while (true) {
                // １商品のループ
                while (true) {
                    // タイトルヘッダーの表示
                    System.out.println("■■■■■■■■■■■■■■■          いらっしゃいませ            ■■■■■■■■■■■■■■■■■■■■■");
                    System.out.println("■■■■■■■■■■■■■■■　ITEカフェシステム　レジ画面（商品一覧） ■■■■■■■■■■■■■■■■■■■■■");

                    // 商品一覧を表示する
                    for (int i = 0; i < items.length; i++) {
                        if ((i + 1) % 4 != 0) {
                            System.out.printf("%-20s", items[i].getNo() + ":" + items[i].getName());
                        } else {
                            System.out.printf("%-20s%n", items[i].getNo() + ":" + items[i].getName());
                        }
                    }
                    // タイトルフッターの表示
                    System.out.println();
                    System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");

                    System.out.print("商品番号を入力してください：");
                    int inputItemNum = Integer.parseInt(sc.next());

                    System.out.print("数量(+/-)を入力してください：");
                    int inputItemCount = Integer.parseInt(sc.next());

                    // カゴに入れる
                    kago.inputKago(items[inputItemNum - 1], inputItemCount);

                    //合計金額を加算する
                    goukeiKin += items[inputItemNum - 1].getPrice() * inputItemCount;

                    // カゴの一覧を表示
                    System.out.println("---------- 注文一覧 ----------");
                    kago.dispKago();
                    System.out.println("------------------------------");

                    System.out.print("お会計=(k)aikei / 継続入力(n)ext：");

                    String inputChar = sc.next();
                    if (inputChar.charAt(0) == 'k') {
                        break;

                    } else if (inputChar.charAt(0) == 'n') {
                        continue;
                    }

                }

                // お会計処理
                // 合計金額の表示
                System.out.println("-------------------------------");
                System.out.println("小計     " + goukeiKin + "円");
                System.out.println("消費税    " + (int) (Math.floor(goukeiKin * 0.08)) + "円");
                goukeiKin = (int) (Math.floor(goukeiKin * 1.08));
                System.out.println("合計金額  " + goukeiKin + "円");
                System.out.println("-------------------------------");

                System.out.print("以上で宜しいですか？=(y)es / 取り消し=(c)ancel : ");
                String inputChar = sc.next();
                if (inputChar.charAt(0) == 'y') {

                    break;

                } else if (inputChar.charAt(0) == 'c') {
                    // 合計金額を0にする
                    goukeiKin = 0;
                    // カゴを空にする
                    kago.clearKago();

                    continue;
                }

            }

            // 代金預かり
            System.out.println("-------------------------------");
            System.out.print("預り金を入力してください：");
            String azukariKin = sc.next();
            int turiKin = Integer.parseInt(azukariKin) - goukeiKin;
            System.out.println("釣銭：" + turiKin + "円");

            // 会計番号計算
            System.out.println(cal.get(Calendar.YEAR) + "年"
                    + cal.get(Calendar.MONTH) + "月"
                    + cal.get(Calendar.DATE) + "日"
                    + cal.get(Calendar.HOUR_OF_DAY) + "時"
                    + cal.get(Calendar.MINUTE) + "分");

            System.out.printf("お会計番号：%06d\n", kaikeiNo);
            kaikeiNo++;

            System.out.println("-------------------------------");

            System.out.println("ありがとうございました 、またのご利用お待ちしております。");

            // 合計金額を0にする
            goukeiKin = 0;
            // カゴを空にする
            kago.clearKago();

        }
    }

}
