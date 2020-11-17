package Module;

public class CalcChargingTime {
    public static int calc(int car_battery,int charge_battery) {
        int count=0;
        int div_hour = charge_battery/6; //10분단위로 쪼갬
        int sum_hour=0;
            while(car_battery >= div_hour) {
                count++;
                sum_hour += div_hour;
            }
            return count;
    }
}
