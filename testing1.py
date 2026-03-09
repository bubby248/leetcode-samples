Conversation opened. 1 unread message.

Skip to content
Using Gmail with screen readers

1 of 24,506
Testing
Inbox

shravya karanam
Attachments
4:54 PM (5 minutes ago)
to me


 One attachment
  •  Scanned by Gmail
from pyspark.sql import DataFrame
from pyspark.sql import functions as F
from pyspark.sql.window import Window

from playback_core.plays_fact_redshift.entity.ContentDim import ContentDim


def update_columns(
    spark, dim_service_table, dim_service_ca_table, updated_df, content_dim_table,process_date
) -> DataFrame:
    updated_df = (
        updated_df.withColumn(
            "etl_metadata_struct",
            F.struct(
                F.current_timestamp().alias("etl_create_est_timestamp"),
                F.current_timestamp().alias("etl_update_est_timestamp"),
            ),
        )
        .withColumn("source", F.lit("Redshift"))
        .withColumn("initial_play_event_id", F.col("cnsmptn_id").cast("string"))
        .withColumn("profile_id", F.coalesce(F.col("profile_id"), F.lit("NA")))
        .withColumn("identity_id", F.coalesce(F.col("identity_id"), F.lit("NA")))
        .withColumn(
            "total_play_time_ms",
            F.when(
                F.col("cnsumd_durn_sec_qty").isNotNull(),
                (F.col("cnsumd_durn_sec_qty") * 1000).cast("int"),
            ).otherwise(F.lit(0)),
        )
        .withColumn("is_test", F.lit(False))
        .withColumn(
            "end_reason",
            F.when(F.col("navgtn_event_nm") == "Play", "Complete")
            .when(F.col("navgtn_event_nm") == "Rewind", "Rewind")
            .when(F.col("navgtn_event_nm") == "Forward", "Seek-forward")
            .otherwise("NA"),
        )
        .withColumn("os_version", F.col("os_ver_nbr"))
        .withColumn("browser_version", F.col("brwsr_ver_nbr"))
        .withColumn("subscription_id", F.col("sbscrptn_id"))
        .withColumn("unified_subscription_id", F.col("sbscrptn_id"))
        .withColumn("date", F.date_format("cnsmptn_start_est_ts", "yyyy-MM-dd"))
        .withColumn(
            "play_id",
            F.concat_ws(
                ":",
                F.col("profile_id"),
                F.col("item_entity_id"),
                F.col("initial_play_event_id"),
            ),
        )
        .withColumn(
            "content_delivery_method",
            F.when(F.col("cntnt_dlvry_mthd_cd") == "IP", "IP")
            .when(F.col("cntnt_dlvry_mthd_cd") == "SAT", "SAT")
            .otherwise("NA"),
        )
        .withColumn("gup_id", F.col("gup_id"))
        .withColumn("session_id", F.lit("NA"))
        .withColumn("token_id", F.lit("NA"))
        .withColumn("is_downloaded", F.lit("false").cast("boolean"))
        .withColumn("tenant_id", F.lit("sxm"))
        .withColumn("service_key_id", F.lit(None).cast("bigint"))
        .withColumn("offer_country_code", F.col("offer_country_code"))
        .withColumn("spin_quantity", F.col("spin_qty").cast("int"))
        .withColumn("broadcast_end_est_timestamp", F.lit(None).cast("timestamp"))
        .withColumn(
            "broadcast_start_est_timestamp",
            F.from_utc_timestamp(F.col("broadcastStartTime"), "EST"),
        )
        .withColumn(
            "play_start_est_timestamp",
            F.from_utc_timestamp(F.col("playStartTime"), "EST"),
        )
        .withColumn(
            "play_end_est_timestamp", F.from_utc_timestamp(F.col("playEndTime"), "EST")
        )
        .withColumn(
            "radio_id",
            F.when(
                F.col("strmng_device_grp_nm") == "Vehicle",
                F.col("device_id_consumption"),
            ).otherwise(F.lit(None).cast("string")),
        )
        .withColumn(
            "streaming_device_platform_id",
            F.col("strmng_device_pltfm_id").cast("string"),
        )
        .withColumn("streaming_device_subtype_name", F.col("strmng_device_subtype_nm"))
        .withColumn(
            "play_end_est_date",
            F.when(
                F.col("play_end_est_timestamp").isNull(),
                F.to_date(F.col("play_start_est_timestamp"), "yyyy-MM-dd"),
            ).otherwise(F.to_date(F.col("play_end_est_timestamp"), "yyyy-MM-dd")),
        )
        .withColumn(
            "device_platform",
            F.when(F.col("strmng_device_type_nm") == "Vehicle", F.lit("360l"))
            .when(
                F.col("strmng_device_grp_nm") == "Pc",
                F.concat_ws(
                    " - ",
                    F.col("strmng_device_type_nm"),
                    F.col("strmng_device_os_pltfm_nm"),
                ),
            )
            .when(
                (F.col("strmng_device_grp_nm") == "Mobile")
                & (F.col("strmng_device_type_nm").isin("Mobile", "Phone"))
                & (
                    F.lower(F.col("strmng_device_subtype_nm")).like("%ip%")
                    | F.lower(F.col("strmng_device_os_pltfm_nm")).like("%io%")
                ),
                F.lit("apple-phone"),
            )
            .when(
                (F.col("strmng_device_grp_nm") == "Mobile")
                & (F.col("strmng_device_type_nm").isin("Mobile", "Phone"))
                & (
                    F.lower(F.col("strmng_device_subtype_nm")).like("%and%")
                    | F.lower(F.col("strmng_device_os_pltfm_nm")).like("%and%")
                ),
                F.lit("android"),
            )
            .when(
                (F.col("strmng_device_grp_nm") == "Mobile")
                & (F.col("strmng_device_type_nm").isin("Mobile", "Phone"))
                & (F.col("strmng_device_subtype_nm") == "Null"),
                F.lit("unknown-phone"),
            )
            .when(
                (F.col("strmng_device_grp_nm") == "Mobile")
                & (F.col("strmng_device_type_nm") == "Tablet")
                & (
                    F.lower(F.col("strmng_device_subtype_nm")).like("%and%")
                    | F.lower(F.col("strmng_device_os_pltfm_nm")).like("%and%")
                ),
                F.lit("android-tablet"),
            )
            .when(
                (F.col("strmng_device_grp_nm") == "Mobile")
                & (F.col("strmng_device_type_nm") == "Tablet")
                & (
                    F.lower(F.col("strmng_device_subtype_nm")).like("%ip%")
                    | F.lower(F.col("strmng_device_os_pltfm_nm")).like("%io%")
                ),
                F.lit("apple-tablet"),
            )
            .when(
                (F.col("strmng_device_grp_nm") == "Mobile")
                & (F.col("strmng_device_type_nm") == "Tablet")
                & (
                    F.lower(F.col("strmng_device_subtype_nm")).like("%wind%")
                    | F.lower(F.col("strmng_device_os_pltfm_nm")).like("%wind%")
                ),
                F.lit("windows-tablet"),
            )
            .when(
                (F.col("strmng_device_grp_nm") == "Mobile")
                & (F.col("strmng_device_type_nm") == "Tablet")
                & (F.col("strmng_device_subtype_nm") == "Null"),
                F.lit("unknown-tablet"),
            )
            .when(
                (F.col("strmng_device_grp_nm") == "Home")
                & (
                    F.col("alt_strmng_device_type_nm").isin(
                        "Home", "Not Defined", "Unknown", "Portable"
                    )
                ),
                F.lit("home"),
            )
            .when(
                (F.col("strmng_device_grp_nm") == "Home")
                & (
                    F.col("alt_strmng_device_type_nm").isin(
                        "Game Console",
                        "Speaker/Smart Speaker/Voice Activated",
                        "Smart TV/OTT STB",
                    )
                ),
                F.lit("ce"),
            )
            .otherwise(F.col("strmng_device_subtype_nm")),
        )
        .withColumn(
            "listen_subscription_exclusion_reason",
            F.when(F.col("unified_subscription_id").like("ccm_%"), "ccm_")
            .when(F.col("unified_subscription_id").like("mm_%"), "mm_")
            .when(F.lower(F.col("unified_subscription_id")).like("%a"), "%a")
            .when(F.col("unified_subscription_id") == "-1", "-1")
            .otherwise("--"),
        )
    )

    item_length_flag_array_df = update_item_length_flag_array(
        spark, content_dim_table, updated_df
    )
    updated_device_df = add_device_group_and_sub_category(item_length_flag_array_df)    # Process for US
    updated_df_us = process_dim_service(
        spark,
        dim_service_table,
        "US",
        updated_device_df,
        "srvc_rec_eff_ts",
        "srvc_rec_exp_ts",
        process_date,
    )
    # Process for CA
    updated_df_ca = process_dim_service(
        spark, dim_service_ca_table, "CA", updated_df_us,
        "rec_eff_ts", "rec_exp_ts", process_date
    )

    return updated_df_ca


def process_dim_service(
    spark,
    dim_service_table,
    country_code,
    updated_df,
    eff_ts_col,
    exp_ts_col,
    process_date
) -> DataFrame:

    # Step 1: Load and select the required columns based on country
    common_cols = [
        "device_id",
        "rec_eff_ts",
        "rec_exp_ts",
        "srvc_sts_cd",
        "atlas_subscription_id",
        "sbscrptn_id",
        "srvc_cd",
        "srvc_key_id",
        "unified_subscription_id"
    ]

    if country_code == "CA":
        dim_service_df = spark.table(dim_service_table).select(*common_cols)
    else:
        dim_service_df = spark.table(dim_service_table).select(
            *common_cols,
            "srvc_rec_eff_ts",
            "srvc_rec_exp_ts"
        )

    # Step 2: Filter active services for 360L logic
    dim_service_360l = (
        dim_service_df
        .filter(
            (F.lit(process_date).between(F.col("rec_eff_ts"), F.col("rec_exp_ts")))
            & (F.col("srvc_cd") != "SIR")
        )
        .withColumn(
            "rn",
            F.row_number().over(
                Window.partitionBy(F.trim(F.col("device_id"))).orderBy(
                    F.when(F.col("srvc_sts_cd") == "A", 1)
                     .when(F.col("srvc_sts_cd") == "F", 2)
                     .when(F.col("srvc_sts_cd") == "S", 3)
                     .when(F.col("srvc_sts_cd") == "N", 4)
                     .when(F.col("srvc_sts_cd") == "C", 5)
                     .otherwise(6),
                    F.col("rec_eff_ts").desc(),
                )
            )
        )
        .filter(F.col("rn") == 1)
        .withColumnRenamed("unified_subscription_id", "ds_unified_subscription_id")
    )

    # Step 3: Join with updated_df for 360L logic
    updated_360l_df = (
        updated_df.alias("pl")
        .join(
            dim_service_360l.alias("ds"),
            F.trim(F.col("ds.device_id")) == F.col("pl.radio_id"),
            "left"
        )
        .withColumn(
            "service_key_id",
            F.when(
                (F.col("pl.offer_country_code") == country_code)
                & (F.col("pl.device_platform") == "360l"),
                F.col("ds.srvc_key_id").cast("bigint")
            ).otherwise(F.col("pl.service_key_id"))
        )
        .withColumn(
            "unified_subscription_id",
            F.when(
                (F.col("pl.offer_country_code") == country_code)
                & (F.col("pl.device_platform") == "360l"),
                F.col("ds.ds_unified_subscription_id")
            ).otherwise(F.col("pl.unified_subscription_id"))
        )
    )

    # Step 4: Filter active Polaris services
    dim_service_polaris = (
        dim_service_df
        .filter(
            (F.col("srvc_cd") == "SIR")
            &
            F.lit(process_date).between(F.col(eff_ts_col), F.col(exp_ts_col))
        )
        .withColumn(
            "rn",
            F.row_number().over(
                Window.partitionBy(F.trim(F.col("device_id"))).orderBy(
                    F.when(F.col("srvc_sts_cd") == "A", 1)
                    .when(F.col("srvc_sts_cd") == "F", 2)
                    .when(F.col("srvc_sts_cd") == "S", 3)
                    .when(F.col("srvc_sts_cd") == "N", 4)
                    .when(F.col("srvc_sts_cd") == "C", 5)
                    .otherwise(6),
                    F.col(eff_ts_col).desc(),
                )
            )
        )
        .filter(F.col("rn") == 1)
        .withColumnRenamed("unified_subscription_id", "ds_unified_subscription_id")
    )

    # Step 5: Final join for Polaris logic
    updated_polaris_df = (
        updated_360l_df.alias("pl")
        .join(
            dim_service_polaris.alias("ds"),
            F.lower(F.col("ds.ds_unified_subscription_id")) == F.lower(F.col("pl.subscription_id")),
            "left"
        )
        .withColumn(
            "service_key_id",
            F.when(
                (F.col("pl.offer_country_code") == country_code)
                & (F.col("pl.device_platform") != "360l")
                & (~F.col("pl.subscription_id").like("mm%"))
                & (~F.col("pl.subscription_id").like("ccm%")),
                F.col("ds.srvc_key_id").cast("bigint")
            ).otherwise(F.col("pl.service_key_id"))
        )
        .withColumn(
            "unified_subscription_id",
            F.when(
                (F.col("pl.offer_country_code") == country_code)
                & (F.col("pl.device_platform") != "360l")
                & (~F.col("pl.subscription_id").like("mm%"))
                & (~F.col("pl.subscription_id").like("ccm%"))
                & (F.col("ds.ds_unified_subscription_id").isNotNull()),
                F.col("ds.ds_unified_subscription_id")
            ).otherwise(F.col("pl.unified_subscription_id"))
        )
    )

    print("Count of 360l dataframe:", updated_360l_df.count())

    return updated_polaris_df


def add_device_group_and_sub_category(result_df) -> DataFrame:
    print("result_df shwoing", result_df.count())
    return (
        result_df.withColumn(
            "device_type_name",
            F.when(F.col("strmng_device_type_nm") == "Vehicle", F.lit("Vehicle"))
            .when(
                (F.col("strmng_device_grp_nm") == "Pc"),
                F.concat_ws(
                    " - ",
                    F.col("strmng_device_type_nm"),
                    F.col("strmng_device_os_pltfm_nm"),
                ),
            )
            .when(
                (F.col("strmng_device_grp_nm") == "Mobile")
                & (F.col("strmng_device_type_nm").isin("Mobile", "Phone"))
                & (
                    F.lower(F.col("strmng_device_subtype_nm")).like("%ip%")
                    | F.lower(F.col("strmng_device_os_pltfm_nm")).like("%io%")
                ),
                F.lit("Phone - iOS"),
            )
            .when(
                (F.col("strmng_device_grp_nm") == "Mobile")
                & (F.col("strmng_device_type_nm").isin("Mobile", "Phone"))
                & (
                    F.lower(F.col("strmng_device_subtype_nm")).like("%and%")
                    | F.lower(F.col("strmng_device_os_pltfm_nm")).like("%and%")
                ),
                F.lit("Phone - Android"),
            )
            .when(
                (F.col("strmng_device_grp_nm") == "Mobile")
                & (F.col("strmng_device_type_nm").isin("Mobile", "Phone"))
                & (F.col("strmng_device_subtype_nm") == "Null"),
                F.lit("Phone - Unknown"),
            )
            .when(
                (F.col("strmng_device_grp_nm") == "Mobile")
                & (F.col("strmng_device_type_nm") == "Tablet")
                & (
                    F.lower(F.col("strmng_device_subtype_nm")).like("%and%")
                    | F.lower(F.col("strmng_device_os_pltfm_nm")).like("%and%")
                ),
                F.lit("Tablet - Android"),
            )
            .when(
                (F.col("strmng_device_grp_nm") == "Mobile")
                & (F.col("strmng_device_type_nm") == "Tablet")
                & (
                    F.lower(F.col("strmng_device_subtype_nm")).like("%ip%")
                    | F.lower(F.col("strmng_device_os_pltfm_nm")).like("%io%")
                ),
                F.lit("Tablet - iOS"),
            )
            .when(
                (F.col("strmng_device_grp_nm") == "Mobile")
                & (F.col("strmng_device_type_nm") == "Tablet")
                & (
                    F.lower(F.col("strmng_device_subtype_nm")).like("%wind%")
                    | F.lower(F.col("strmng_device_os_pltfm_nm")).like("%wind%")
                ),
                F.lit("Tablet - Windows"),
            )
            .when(
                (F.col("strmng_device_grp_nm") == "Mobile")
                & (F.col("strmng_device_type_nm") == "Tablet")
                & (F.col("strmng_device_subtype_nm") == "Null"),
                F.lit("Tablet - Unknown"),
            )
            .when(
                (F.col("strmng_device_grp_nm") == "Home")
                & (
                    F.col("alt_strmng_device_type_nm").isin(
                        "Home", "Not Defined", "Unknown", "Portable"
                    )
                ),
                F.lit("Home - Other"),
            )
            .when(
                (F.col("strmng_device_grp_nm") == "Home")
                & (
                    F.col("alt_strmng_device_type_nm").isin(
                        "Game Console",
                        "Speaker/Smart Speaker/Voice Activated",
                        "Smart TV/OTT STB",
                    )
                ),
                F.lit("CE"),
            )
            .otherwise(F.lit("Other")),
        )
        .withColumn(
            "device_group",
            F.when(F.col("device_type_name") == "Vehicle", F.lit("Vehicle"))
            .when(
                F.col("device_type_name").isin(
                    "Phone - Android",
                    "Phone - iOS",
                    "Tablet - Android",
                    "Tablet - iOS",
                    "Tablet",
                    "Smartphone",
                    "Tablet - iOS",
                ),
                F.lit("Mobile"),
            )
            .when(F.col("device_type_name") == "Pc - Commercial", F.lit("Commercial"))
            .when(F.col("device_type_name").like("Pc%"), F.lit("Web"))
            .when(F.col("device_type_name").like("Connected Devices"), F.lit("CE"))
            .when(F.col("device_type_name") == "automotive-tesla", F.lit("Vehicle"))
            .when(F.col("device_type_name") == "CE", F.lit("CE"))
            .when(F.col("device_type_name") == "Home - Other", F.lit("CE"))
            .otherwise(F.lit("Other")),
        )
        .withColumn(
            "device_sub_name",
            F.when(
                (F.col("strmng_device_subtype_nm") == "Amazonalexa")
                | (F.lower(F.col("strmng_device_nm")).like("%alexa%")),
                F.lit("Amazon Alexa"),
            )
            .when(F.col("strmng_device_subtype_nm") == "Amazon", F.lit("AmazonFireTV"))
            .when(
                F.col("strmng_device_subtype_nm").like("%Chromecast%"), F.lit("Google")
            )
            .when(F.col("strmng_device_subtype_nm").like("Bose%"), F.lit("Bose"))
            .when(F.col("strmng_device_subtype_nm").like("Sonos%"), F.lit("Sonos"))
            .when(F.col("strmng_device_subtype_nm").like("Yamaha%"), F.lit("Yamaha"))
            .when(F.col("strmng_device_subtype_nm").like("Roku%"), F.lit("Roku"))
            .when(F.col("strmng_device_subtype_nm").like("Denon%"), F.lit("Denon"))
            .when(F.col("strmng_device_subtype_nm").like("%Comcast%"), F.lit("Comcast"))
            .otherwise(F.col("strmng_device_subtype_nm")),
        )
        .withColumn(
            "device_sub_category",
            F.when(F.col("device_type_name") == "Vehicle", F.lit("OEM"))
            .when(
                (F.col("device_type_name") == "Smartphone")
                & (F.col("device_platform") == "Android"),
                F.lit("Mobile - Android"),
            )
            .when(
                (F.col("device_type_name") == "Smartphone")
                & (F.col("device_platform") == "IOS"),
                F.lit("Smartphone - iOS"),
            )
            .when(
                (F.col("device_type_name") == "Tablet")
                & (F.col("device_platform") == "IOS"),
                F.lit("Tablet - iOS"),
            )
            .when(
                (F.col("device_type_name") == "Tablet")
                & (F.col("device_platform") == "Other"),
                F.lit("Tablet"),
            )
            .when(F.col("device_type_name") == "automotive-tesla", F.lit("OEM"))
            .when(F.col("device_sub_name").isin("Denon", "Yamaha"), F.lit("Receiver"))
            .when(
                F.col("device_sub_name").isin("Amazon Alexa", "Sonos"),
                F.lit("Smart Speaker"),
            )
            .when(
                (F.col("device_type_name") == "CE")
                & (F.col("device_sub_name").isin("Samsung", "Lg")),
                F.lit("TV"),
            )
            .when(
                (F.col("device_type_name") == "CE")
                & (F.col("device_sub_name") == "Grace"),
                F.lit("Receiver And Speaker"),
            )
            .when(
                (F.col("device_type_name") == "CE")
                & (F.col("device_sub_name") == "Google"),
                F.lit("Smart Speaker or TV"),
            )
            .when(
                F.col("device_sub_name").isin(
                    "AmazonFireTV",
                    "Hisense",
                    "Hkc",
                    "Nvidia",
                    "Sharp",
                    "Sony",
                    "Tcl",
                    "Dish",
                    "Roku",
                ),
                F.lit("TV"),
            )
            .when(
                (F.col("device_type_name") == "CE")
                & F.col("device_platform").isin(
                    "Androidtv",
                    "Apple Tvos",
                    "Lg",
                    "Lgeverest",
                    "Roku",
                    "Rokueverest",
                    "Rokueverestv4",
                    "Samsung",
                    "Samsungeverest",
                    "Tvos",
                    "Vizioeverest",
                    "Comcasteverest",
                    "Comcasteverestv4",
                ),
                F.lit("TV"),
            )
            .when(
                (F.col("device_type_name") == "CE")
                & F.col("device_platform").isin(
                    "Autonomic",
                    "Autonomiceverest",
                    "Bluesound",
                    "Control4",
                    "Denoneverest",
                    "Nuvoeverest",
                    "Play-Fi",
                    "Russound",
                ),
                F.lit("Receiver"),
            )
            .when(
                (F.col("device_type_name") == "CE")
                & F.col("device_platform").isin("Boseeverest", "Grace", "Graceeverest"),
                F.lit("Receiver And Speaker"),
            )
            .when(
                (F.col("device_type_name") == "CE")
                & (F.col("device_platform") == "Sonoseverest"),
                F.lit("Smart Speaker"),
            )
            .when(F.col("device_type_name") == "CE", F.lit("Other"))
            .when(F.col("device_type_name") == "Home - Other", F.lit("Other"))
            .when(F.col("device_type_name") == "Pc - Commercial", F.lit("Commercial"))
            .when(
                F.col("device_type_name").isin(
                    "Pc - Linux", "Pc - Macosx", "Pc - Na", "Pc - Other", "Pc - Windows"
                ),
                F.lit("Web - Desktop"),
            )
            .when(
                F.col("device_type_name") == "Phone - Android",
                F.lit("Smartphone - Android"),
            )
            .when(F.col("device_type_name") == "Phone - iOS", F.lit("Smartphone - iOS"))
            .when(
                F.col("device_type_name") == "Tablet - Android",
                F.lit("Tablet - Android"),
            )
            .when(F.col("device_type_name") == "Tablet - iOS", F.lit("Tablet - iOS"))
            .otherwise(F.lit("Other")),
        )
    )


def update_item_length_flag_array(
    spark, content_dim_table, device_group_df
) -> DataFrame:
    content_dim = ContentDim(content_dim_table)
    content_dim_df = content_dim.get_content_dim(spark)

    device_group_df = device_group_df.join(
        content_dim_df,
        device_group_df["item_entity_id"] == content_dim_df["itemEntityId"],
        "left",
    )
    print("device_group_df", device_group_df.show(10))

    return (
        device_group_df.withColumn(
            "item_length_ms",
            F.when(
                (
                    (F.col("adjusted_duration_ms").isNotNull())
                    | (F.col("adjusted_duration_ms") != 0)
                ),
                F.col("adjusted_duration_ms"),
            )
            .when((F.col("duration_ms").isNotNull()), F.col("duration_ms"))
            .otherwise(F.lit(0)),
        )
        .withColumn(
            "flag_array",
            F.when(
                F.array_contains(F.col("cd_flag_array"), "AD"), F.array(F.lit("AD"))
            ).otherwise(F.lit(None)),
        )
        .drop("adjusted_duration_ms", "duration_ms", "cd_flag_array")
    )


def select_final_columns(result_df: DataFrame):
    return result_df.select(
        "offer_country_code",
        "broadcast_start_est_timestamp",
        "play_start_est_timestamp",
        "play_end_est_timestamp",
        "source_entity_id",
        "source_entity_type",
        F.col("item_entity_id").alias("content_entity_id"),
        F.col("item_entity_type").alias("content_entity_type"),
        "device_platform",
        "device_group",
        "device_sub_category",
        "content_delivery_method",
        "source",
        "initial_play_event_id",
        "profile_id",
        "identity_id",
        "gup_id",
        "total_play_time_ms",
        "broadcast_end_est_timestamp",
        "etl_metadata_struct",
        "is_test",
        "end_reason",
        "os_version",
        "browser_version",
        "subscription_id",
        "unified_subscription_id",
        "play_end_est_date",
        "play_id",
        "spin_quantity",
        "radio_id",
        "item_length_ms",
        "session_id",
        "token_id",
        "is_downloaded",
        "tenant_id",
        "service_key_id",
        "listen_subscription_exclusion_reason",
        "legacy_ids",
        "flag_array",
    )
device_details.py
Displaying device_details.py.
