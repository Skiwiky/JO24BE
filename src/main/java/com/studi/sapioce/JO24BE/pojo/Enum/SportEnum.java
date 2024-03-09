package com.studi.sapioce.JO24BE.pojo.Enum;

public enum SportEnum {
	
	    ATHLETISME("l'athlétisme"),
	    AVIRON("l'aviron"),
	    BADMINTON("le badminton"),
	    BASKETBALL("le basketball"),
	    BASKETBALL_3X3("le basketball 3x3"),
	    BOXE("la boxe"),
	    CANOE_SPRINT("le canoë sprint"),
	    CANOE_KAYAK_SALOM("le canoë-kayak slalom"),
	    CYCLISME_SUR_PISTE("le cyclisme sur piste"),
	    CYCLISME_SUR_ROUTE("le cyclisme sur route"),
	    BMX_FREESTYLE("le BMX freestyle"),
	    BMX_RACING("le BMX racing"),
	    MOUNTAIN_BIKE("le mountain bike (VTT)"),
	    ESCRIME("l'escrime"),
	    FOOTBALL("le football"),
	    GOLF("le golf"),
	    GYMNASTIQUE_ARTISTIQUE("la gymnastique artistique"),
	    GYMNASTIQUE_RYTHMIQUE("la gymnastique rythmique"),
	    TRAMPOLINE("le trampoline"),
	    HALTEROPHILIE("l'haltérophilie"),
	    HANDBALL("le handball"),
	    HOCKEY("le hockey"),
	    JUDO("le judo"),
	    LUTTE("la lutte"),
	    PENTATHLON_MODERNE("le pentathlon moderne"),
	    RUGBY("le rugby"),
	    NATATION("la natation"),
	    NATATION_ARTISTIQUE("la natation artistique"),
	    NATATION_MARATHON("la natation marathon"),
	    PLONGEON("le plongeon"),
	    WATERPOLO("le waterpolo"),
	    SPORTS_EQUESTRES("les sports équestres"),
	    TAEKWONDO("le taekwondo"),
	    TENNIS("le tennis"),
	    TENNIS_DE_TABLE("le tennis de table"),
	    TIR("le tir"),
	    TIR_A_LARC("le tir à l’arc"),
	    TRIATHLON("le triathlon"),
	    VOILE("la voile"),
	    VOLLEYBALL("le volleyball"),
	    VOLLEYBALL_DE_PLAGE("le volleyball de plage"),
	    BREAKING("le breaking"),
	    ESCALADE_SPORTIVE("l'escalade sportive"),
	    SKATEBOARD("le skateboard"),
	    SURF("le surf");

	    private final String nomComplet;

	    SportEnum(String nomComplet) {
	        this.nomComplet = nomComplet;
	    }

	    public String getNomComplet() {
	        return nomComplet;
	    }

}
