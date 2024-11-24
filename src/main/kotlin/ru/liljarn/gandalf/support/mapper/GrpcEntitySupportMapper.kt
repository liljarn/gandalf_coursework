package ru.liljarn.gandalf.support.mapper

import com.google.protobuf.Timestamp
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.UUID

fun String.toUuid() = UUID.fromString(this)

fun LocalDate.toTimestamp(): Timestamp =
    Timestamp
        .newBuilder()
        .setSeconds(this.atStartOfDay().toEpochSecond(ZoneOffset.UTC))
        .build()
