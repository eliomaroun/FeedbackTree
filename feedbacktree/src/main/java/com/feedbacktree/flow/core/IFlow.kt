package com.feedbacktree.flow.core

import io.reactivex.Observable

sealed class FlowOutput<out Output> {
    object Aborted : FlowOutput<Nothing>()
    data class Completed<out Output>(val result: Output) : FlowOutput<Output>()
}

fun <Output> completed(success: Output): FlowOutput<Output> =
    FlowOutput.Completed(success)

fun <Output> aborted(): FlowOutput<Output> =
    FlowOutput.Aborted

interface IFlow<Input, Output> {
    fun run(input: Input): Observable<FlowOutput<Output>>
}