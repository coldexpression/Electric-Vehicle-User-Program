package Module;

public class CalcChargingTime {
    public static int calc(int max_car_battery,int now_car_battery,int charge_battery) {
        int count=0;
        int div_hour = charge_battery/6; //10분단위로 쪼갬
        int sum_hour=0;
        int car_battery = max_car_battery - now_car_battery;
            while(car_battery >= sum_hour) {
                count++;
                sum_hour += div_hour;
                System.out.println(sum_hour);
            }
            return (count-1)*10;
    }
    public static int trans(int max_car_battery,int percent_now_car_battery) {
        int count=1;
        while((count*100)/max_car_battery < percent_now_car_battery) {
            count++;
        }
        return count;
    }
}
