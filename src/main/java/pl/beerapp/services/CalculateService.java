package pl.beerapp.services;

import org.springframework.stereotype.Service;

@Service
public class CalculateService {

    /**
     * Calculate MCU.
     *
     * @param grainKg  grain weight
     * @param colorEBC grain color
     * @param volume   water volume
     * @return calculated MCU
     */
    public Double calculateMCU(Double grainKg, Double colorEBC, Double volume) {
        Double MCU;
        Double WeightOfGrainInLbs = grainKg * 2.2046;
        Double ColorOfGrainInDegreesLovibond = calculateEBCtoLovibond(colorEBC);
        Double VolumeInGallons = volume * 0.21997;

        MCU = (WeightOfGrainInLbs * ColorOfGrainInDegreesLovibond) / (VolumeInGallons);

        return MCU;
    }

    /**
     * Calculate SRM from MCU.
     *
     * @param MCU mcu value
     * @return calculated SRM value
     */
    public Double calculateSRM(Double MCU) {
        Double SRM;
        SRM = 1.49 * Math.pow(MCU, 0.69);

        return SRM;
    }

    /**
     * Return right color by te SRM value.
     * Colors by http://www.homebrewtalk.com/showthread.php?t=78018&page=2
     *
     * @param SRM srm value
     * @return color in hex string
     */
    public String returnColor(Double SRM) {
        String color;
        String[] colorArray = {
                "#FFE699",
                "#FFD878",
                "#FFCA5A",
                "#FFBF42",
                "#FBB123",
                "#F8A600",
                "#F39C00",
                "#EA8F00",
                "#E58500",
                "#DE7C00",
                "#D77200",
                "#CF6900",
                "#CB6200",
                "#C35900",
                "#BB5100",
                "#B54C00",
                "#B04500",
                "#A63E00",
                "#A13700",
                "#9B3200",
                "#952D00",
                "#8E2900",
                "#882300",
                "#821E00",
                "#7B1A00",
                "#771900",
                "#701400",
                "#6A0E00",
                "#660D00",
                "#5E0B00",
                "#5A0A02",
                "#600903",
                "#520907",
                "#4C0505",
                "#470606",
                "#440607",
                "#3F0708",
                "#3B0607",
                "#3A070B",
                "#36080A"
        };

        if (SRM <= 40 && SRM > 0) {
            color = colorArray[SRM.intValue() - 1];
        } else if (SRM == 0) {
            color = "#FFFFFF";
        } else {
            color = "#000000";
        }

        return color;
    }

    /**
     * Calculate EBC to Lovibond deegres
     *
     * @param EBC color of malt in ebc
     * @return calculated lovibond
     */
    private Double calculateEBCtoLovibond(Double EBC) {
        Double Lovibond;
        Double SRM;
        SRM = EBC * 0.508;
        Lovibond = (SRM + 0.76) / 1.3546;
        return Lovibond;
    }

    /**
     * Calculate Malt Specific Gravity
     * by http://homebrew.stackexchange.com/questions/1434/wiki-how-do-you-calculate-original-gravity
     *
     * @param weightInKG        weight in Kgs
     * @param potentialInPoints potential
     * @param VolumeInLiters    water volume in liters
     * @param efficiency        efficiency in %
     * @return malt sg point
     */
    public Double calculateMaltSG(Double weightInKG, Double potentialInPoints, Double VolumeInLiters, Double efficiency) {
        Double SG;
        Double weightInLB = calculateKGtoLBS(weightInKG);
        Double VolumeInGallons = calculateLitersToGallons(VolumeInLiters);

        SG = (weightInLB * potentialInPoints * (efficiency / 100)) / VolumeInGallons;
        SG = (SG / 1000) + 1; // convert = example: SG = 48, after 1.048
        return calculateSGtoPlato(SG);
    }

    /**
     * Calculate IBU by Rager method.
     * http://www.realbeer.com/hops/FAQ.html#units
     *
     * @param weightInG      wight of hops in grams
     * @param timeInMinutes  hop's time boiling
     * @param Alpha          alpha of hop
     * @param volumeInLiters boil water volume in liters
     * @param boilGravity    gravity when boil
     * @return calculated IBU
     */
    public Double calculateIBURager(Double weightInG, Double timeInMinutes, Double Alpha, Double volumeInLiters, Double boilGravity) {
        Double IBU;
        Double GA;
        Double utilization = 18.11 + 13.86 * Math.tanh((timeInMinutes - 31.32) / 18.27);
        if (boilGravity > 1.050) {
            GA = (boilGravity - 1.050) / 0.2;
        } else {
            GA = 0.0;
        }
        IBU = (weightInG * utilization * (Alpha / 100) * 1000) / (volumeInLiters * (1 + GA));
        return IBU;
    }

    /**
     * Calculate ABV (alcohol by volume) using OG and FG points.
     *
     * @param OG original gravity
     * @param FG final gravity
     * @return calculated abv
     */
    public Double calculateABV(Double OG, Double FG) {
        return (calculatePlatoToSG(OG) - calculatePlatoToSG(FG)) * 131;
    }

    /**
     * Calculate Boil Gravity.
     *
     * @param OG          origin gravity
     * @param boilVolume  est. boil water volume
     * @param finalVolume batch size
     * @return Boil Gravity
     */
    public Double calculateBoilGravity(Double OG, Double boilVolume, Double finalVolume) {
        return (boilVolume / finalVolume) * ((OG - 1) / 100);
    }

    /* ------------------------- Conversions ------------------------- */

    private Double calculateLitersToGallons(Double Liters) {
        return (Liters / 3.785411784);
    }

    private Double calculateGallonsToLiters(Double Gallons) {
        return (Gallons * 3.785411784);
    }

    private Double calculateLBtoKG(Double LB) {
        return (LB / 2.2046);
    }

    private Double calculateKGtoLBS(Double KG) {
        return (KG * 2.2046);
    }

    private Double calculateSGtoPlato(Double SG) {
        return (-1 * 616.868) + +(1111.14 * SG) - (630.272 * Math.pow(SG, 2)) + (135.997 * Math.pow(SG, 3));
    }

    private Double calculatePlatoToSG(Double Plato) {
        return 1 + (Plato / (258.6 - ((Plato / 258.2) * 227.1)));
    }
}
