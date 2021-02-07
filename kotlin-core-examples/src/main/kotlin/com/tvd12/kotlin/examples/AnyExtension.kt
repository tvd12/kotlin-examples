package com.tvd12.kotlin.examples

import java.time.*
import java.util.*

fun Date.toLocalDate() =
    ZonedDateTime
        .ofInstant(toInstant(), ZoneId.systemDefault())
        .toLocalDate()

fun Date.toLocalDateTime() =
    ZonedDateTime
        .ofInstant(toInstant(), ZoneId.systemDefault())
        .toLocalDateTime()

fun LocalDate.toDate() =
    Date.from(atStartOfDay(ZoneId.systemDefault()).toInstant())

fun LocalDateTime.toDate() =
    Date.from(atZone(ZoneId.systemDefault()).toInstant())