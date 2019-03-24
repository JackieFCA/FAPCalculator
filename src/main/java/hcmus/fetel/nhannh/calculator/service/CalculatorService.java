package hcmus.fetel.nhannh.calculator.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import hcmus.fetel.nhannh.calculator.dto.RequestDTO;
import hcmus.fetel.nhannh.calculator.dto.ResponseDTO;
import hcmus.fetel.nhannh.calculator.model.AdPackage;
import hcmus.fetel.nhannh.calculator.model.GroupAdPack;

@Service
public class CalculatorService {
    private static final int LIFE_PERIOD = 120;
    private static final int ADPACK_VALUE = 50;
    private static final int LIMIT_ADPACK = 1000;
    private static final double RATE = 0.0095;

    private static int newAdPacks = 0;
    private static int reduceAdPacks = 0;
    private static int adPacksNo = 0;

    private static double totalCapital = 0;
    private static double dailyProfit = 0;
    private static double totalProfit = 0;
    private static double accountBalance = 0;

    public List<ResponseDTO> calculator(RequestDTO requestDTO) {
        List<ResponseDTO> result = new ArrayList<ResponseDTO>();
        int startAdPack = requestDTO.getNumAdpack();
        int duration = requestDTO.getDuration();
        boolean isReinvest = requestDTO.isReinvest();

        totalCapital = startAdPack * ADPACK_VALUE;
        dailyProfit = totalCapital * RATE;

        adPacksNo = startAdPack;

        List<GroupAdPack> adPacks = new ArrayList<GroupAdPack>();
        adPacks.add(createGroupPackage(startAdPack, createAdpack()));

        for (int day = 1; day <= duration; day++) {
            countDownDay(adPacks);
            totalProfit += dailyProfit;
            accountBalance += dailyProfit;

            if (isReinvest && accountBalance >= ADPACK_VALUE && adPacksNo < LIMIT_ADPACK) {
                double currentBalance = accountBalance;
                int missAdPack = LIMIT_ADPACK - adPacksNo;
                do {
                    newAdPacks++;
                    currentBalance -= ADPACK_VALUE;
                    if (newAdPacks == missAdPack) {
                        break;
                    }
                } while (currentBalance - ADPACK_VALUE >= ADPACK_VALUE);

                accountBalance -= ADPACK_VALUE * newAdPacks;
                if (newAdPacks != 0) {
                    adPacksNo += newAdPacks;
                    totalCapital = adPacksNo * ADPACK_VALUE;
                    dailyProfit = totalCapital * RATE;

                    adPacks.add(createGroupPackage(newAdPacks, createAdpack()));
                }
                result.add(createResponse(day, totalCapital, adPacksNo, dailyProfit, totalProfit, accountBalance,
                        newAdPacks, reduceAdPacks));
            } else {
                result.add(createResponse(day, totalCapital, adPacksNo, dailyProfit, totalProfit, accountBalance,
                        newAdPacks, reduceAdPacks));
            }
            newAdPacks = 0;
            reduceAdPacks = 0;
        }
        totalProfit = 0;
        accountBalance = 0;
        return result;
    }

    private AdPackage createAdpack() {
        return new AdPackage(LIFE_PERIOD, ADPACK_VALUE);
    }

    private GroupAdPack createGroupPackage(int numAd, AdPackage adPack) {
        return new GroupAdPack(numAd, adPack);
    }

    private ResponseDTO createResponse(int day, double totalCapital, int adPacksNo, double dailyProfit,
            double totalProfit, double accountBalance, int newAdPacks, int reduceAdPacks) {
        ResponseDTO response = new ResponseDTO();
        response.setDay(day);
        response.setTotalCapital(round(totalCapital, 2));
        response.setAdpackNum(adPacksNo);
        response.setDailyProfit(round(dailyProfit, 2));
        response.setTotalProfit(round(totalProfit, 2));
        response.setAccountBalance(round(accountBalance, 2));
        response.setNewAdpack(newAdPacks);
        response.setReduceAdpack(reduceAdPacks);
        return response;
    }

    private void countDownDay(List<GroupAdPack> adPacks) {
        List<Integer> indexes = new ArrayList<Integer>();
        for (GroupAdPack currentAdPack : adPacks) {
            AdPackage adPackage = currentAdPack.getAdPackage();
            int duration = adPackage.getDuration() - 1;

            if (duration <= 0) {
                reduceAdPacks = currentAdPack.getVolume();
                adPacksNo -= reduceAdPacks;
                totalCapital = adPacksNo * ADPACK_VALUE;
                dailyProfit = totalCapital * RATE;
                indexes.add(adPacks.indexOf(currentAdPack));
            } else {
                adPackage.setDuration(duration);
                currentAdPack.setAdPackage(adPackage);
            }
        }

        for (Integer index : indexes) {
            adPacks.remove(adPacks.get(index));
        }
    }

    private double round(double value, int places) {
        BigDecimal number = new BigDecimal(value);
        number = number.setScale(places, RoundingMode.HALF_UP);
        return number.doubleValue();
    }
}
