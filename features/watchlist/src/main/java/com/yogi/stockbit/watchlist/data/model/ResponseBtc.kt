package com.yogi.stockbit.watchlist.data.model

import com.google.gson.annotations.SerializedName
import com.yogi.stockbit.watchlist.domain.model.BtcMdl


/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */

data class ResponseBtc(
    @SerializedName("Data")
    val data: List<Data>? = null,
    @SerializedName("HasWarning")
    val hasWarning: Boolean? = false,
    @SerializedName("Message")
    val message: String? = null,
    @SerializedName("RateLimit")
    val rateLimit: RateLimit? = null,
    @SerializedName("SponsoredData")
    val sponsoredData: List<Any>? = null,
    @SerializedName("Type")
    val type: Int? = null
)

internal fun ResponseBtc.toListBtc(): MutableList<BtcMdl> {
    val list: MutableList<BtcMdl> = mutableListOf()
    data?.forEach {
        list.add(
            BtcMdl(
                id = it.rAW?.usd?.lASTTRADEID,
                title = it.coinInfo?.name,
                name = it.coinInfo?.fullName,
                price = it.dISPLAY?.usd?.pRICE,
                changePriceHourDisplay = it.dISPLAY?.usd?.cHANGEHOUR,
                changePriceHour = it.rAW?.usd?.cHANGEHOUR,
                changePercentHour = it.dISPLAY?.usd?.cHANGEPCTHOUR
            )
        )
    }
    return list

}

data class Data(
    @SerializedName("CoinInfo")
    val coinInfo: CoinInfo?,
    @SerializedName("DISPLAY")
    val dISPLAY: DISPLAY?,
    @SerializedName("RAW")
    val rAW: RAW?
)

class RateLimit

data class CoinInfo(
    @SerializedName("Algorithm")
    val algorithm: String?,
    @SerializedName("BlockNumber")
    val blockNumber: Int?,
    @SerializedName("BlockReward")
    val blockReward: Double?,
    @SerializedName("BlockTime")
    val blockTime: Int?,
    @SerializedName("DocumentType")
    val documentType: String?,
    @SerializedName("FullName")
    val fullName: String?,
    @SerializedName("Id")
    val id: String?,
    @SerializedName("ImageUrl")
    val imageUrl: String?,
    @SerializedName("Internal")
    val `internal`: String?,
    @SerializedName("Name")
    val name: String?,
    @SerializedName("NetHashesPerSecond")
    val netHashesPerSecond: Double?,
    @SerializedName("ProofType")
    val proofType: String?,
    @SerializedName("Rating")
    val rating: Rating?,
    @SerializedName("Type")
    val type: Int?,
    @SerializedName("Url")
    val url: String?
)

data class DISPLAY(
    @SerializedName("IDR")
    val iDR: IDR?,
    @SerializedName("USD")
    val usd: IDR?
)

data class RAW(
    @SerializedName("IDR")
    val iDR: IDRX?,
    @SerializedName("USD")
    val usd: IDRX?
)

data class Rating(
    @SerializedName("Weiss")
    val weiss: Weiss?
)

data class Weiss(
    @SerializedName("MarketPerformanceRating")
    val marketPerformanceRating: String?,
    @SerializedName("Rating")
    val rating: String?,
    @SerializedName("TechnologyAdoptionRating")
    val technologyAdoptionRating: String?
)

data class IDR(
    @SerializedName("CHANGE24HOUR")
    val cHANGE24HOUR: String?,
    @SerializedName("CHANGEDAY")
    val cHANGEDAY: String?,
    @SerializedName("CHANGEHOUR")
    val cHANGEHOUR: String?,
    @SerializedName("CHANGEPCT24HOUR")
    val cHANGEPCT24HOUR: String?,
    @SerializedName("CHANGEPCTDAY")
    val cHANGEPCTDAY: String?,
    @SerializedName("CHANGEPCTHOUR")
    val cHANGEPCTHOUR: String?,
    @SerializedName("CONVERSIONSYMBOL")
    val cONVERSIONSYMBOL: String?,
    @SerializedName("CONVERSIONTYPE")
    val cONVERSIONTYPE: String?,
    @SerializedName("FROMSYMBOL")
    val fROMSYMBOL: String?,
    @SerializedName("HIGH24HOUR")
    val hIGH24HOUR: String?,
    @SerializedName("HIGHDAY")
    val hIGHDAY: String?,
    @SerializedName("HIGHHOUR")
    val hIGHHOUR: String?,
    @SerializedName("IMAGEURL")
    val iMAGEURL: String?,
    @SerializedName("LASTMARKET")
    val lASTMARKET: String?,
    @SerializedName("LASTTRADEID")
    val lASTTRADEID: String?,
    @SerializedName("LASTUPDATE")
    val lASTUPDATE: String?,
    @SerializedName("LASTVOLUME")
    val lASTVOLUME: String?,
    @SerializedName("LASTVOLUMETO")
    val lASTVOLUMETO: String?,
    @SerializedName("LOW24HOUR")
    val lOW24HOUR: String?,
    @SerializedName("LOWDAY")
    val lOWDAY: String?,
    @SerializedName("LOWHOUR")
    val lOWHOUR: String?,
    @SerializedName("MARKET")
    val mARKET: String?,
    @SerializedName("MKTCAP")
    val mKTCAP: String?,
    @SerializedName("OPEN24HOUR")
    val oPEN24HOUR: String?,
    @SerializedName("OPENDAY")
    val oPENDAY: String?,
    @SerializedName("OPENHOUR")
    val oPENHOUR: String?,
    @SerializedName("PRICE")
    val pRICE: String?,
    @SerializedName("SUPPLY")
    val sUPPLY: String?,
    @SerializedName("TOPTIERVOLUME24HOUR")
    val tOPTIERVOLUME24HOUR: String?,
    @SerializedName("TOPTIERVOLUME24HOURTO")
    val tOPTIERVOLUME24HOURTO: String?,
    @SerializedName("TOSYMBOL")
    val tOSYMBOL: String?,
    @SerializedName("TOTALTOPTIERVOLUME24H")
    val tOTALTOPTIERVOLUME24H: String?,
    @SerializedName("TOTALTOPTIERVOLUME24HTO")
    val tOTALTOPTIERVOLUME24HTO: String?,
    @SerializedName("TOTALVOLUME24H")
    val tOTALVOLUME24H: String?,
    @SerializedName("TOTALVOLUME24HTO")
    val tOTALVOLUME24HTO: String?,
    @SerializedName("VOLUME24HOUR")
    val vOLUME24HOUR: String?,
    @SerializedName("VOLUME24HOURTO")
    val vOLUME24HOURTO: String?,
    @SerializedName("VOLUMEDAY")
    val vOLUMEDAY: String?,
    @SerializedName("VOLUMEDAYTO")
    val vOLUMEDAYTO: String?,
    @SerializedName("VOLUMEHOUR")
    val vOLUMEHOUR: String?,
    @SerializedName("VOLUMEHOURTO")
    val vOLUMEHOURTO: String?
)

data class IDRX(
    @SerializedName("CHANGE24HOUR")
    val cHANGE24HOUR: Double?,
    @SerializedName("CHANGEDAY")
    val cHANGEDAY: Double?,
    @SerializedName("CHANGEHOUR")
    val cHANGEHOUR: Double?,
    @SerializedName("CHANGEPCT24HOUR")
    val cHANGEPCT24HOUR: Double?,
    @SerializedName("CHANGEPCTDAY")
    val cHANGEPCTDAY: Double?,
    @SerializedName("CHANGEPCTHOUR")
    val cHANGEPCTHOUR: Double?,
    @SerializedName("CONVERSIONSYMBOL")
    val cONVERSIONSYMBOL: String?,
    @SerializedName("CONVERSIONTYPE")
    val cONVERSIONTYPE: String?,
    @SerializedName("FLAGS")
    val fLAGS: String?,
    @SerializedName("FROMSYMBOL")
    val fROMSYMBOL: String?,
    @SerializedName("HIGH24HOUR")
    val hIGH24HOUR: Double?,
    @SerializedName("HIGHDAY")
    val hIGHDAY: Double?,
    @SerializedName("HIGHHOUR")
    val hIGHHOUR: Double?,
    @SerializedName("IMAGEURL")
    val iMAGEURL: String?,
    @SerializedName("LASTMARKET")
    val lASTMARKET: String?,
    @SerializedName("LASTTRADEID")
    val lASTTRADEID: String?,
    @SerializedName("LASTUPDATE")
    val lASTUPDATE: Int?,
    @SerializedName("LASTVOLUME")
    val lASTVOLUME: Double?,
    @SerializedName("LASTVOLUMETO")
    val lASTVOLUMETO: Double?,
    @SerializedName("LOW24HOUR")
    val lOW24HOUR: Double?,
    @SerializedName("LOWDAY")
    val lOWDAY: Double?,
    @SerializedName("LOWHOUR")
    val lOWHOUR: Double?,
    @SerializedName("MARKET")
    val mARKET: String?,
    @SerializedName("MEDIAN")
    val mEDIAN: Double?,
    @SerializedName("MKTCAP")
    val mKTCAP: Double?,
    @SerializedName("OPEN24HOUR")
    val oPEN24HOUR: Double?,
    @SerializedName("OPENDAY")
    val oPENDAY: Double?,
    @SerializedName("OPENHOUR")
    val oPENHOUR: Double?,
    @SerializedName("PRICE")
    val pRICE: Double?,
    @SerializedName("SUPPLY")
    val sUPPLY: Double?,
    @SerializedName("TOPTIERVOLUME24HOUR")
    val tOPTIERVOLUME24HOUR: Double?,
    @SerializedName("TOPTIERVOLUME24HOURTO")
    val tOPTIERVOLUME24HOURTO: Double?,
    @SerializedName("TOSYMBOL")
    val tOSYMBOL: String?,
    @SerializedName("TOTALTOPTIERVOLUME24H")
    val tOTALTOPTIERVOLUME24H: Double?,
    @SerializedName("TOTALTOPTIERVOLUME24HTO")
    val tOTALTOPTIERVOLUME24HTO: Double?,
    @SerializedName("TOTALVOLUME24H")
    val tOTALVOLUME24H: Double?,
    @SerializedName("TOTALVOLUME24HTO")
    val tOTALVOLUME24HTO: Double?,
    @SerializedName("TYPE")
    val tYPE: String?,
    @SerializedName("VOLUME24HOUR")
    val vOLUME24HOUR: Double?,
    @SerializedName("VOLUME24HOURTO")
    val vOLUME24HOURTO: Double?,
    @SerializedName("VOLUMEDAY")
    val vOLUMEDAY: Double?,
    @SerializedName("VOLUMEDAYTO")
    val vOLUMEDAYTO: Double?,
    @SerializedName("VOLUMEHOUR")
    val vOLUMEHOUR: Double?,
    @SerializedName("VOLUMEHOURTO")
    val vOLUMEHOURTO: Double?
)

