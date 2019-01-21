package mvp

/**
 * Created by steve_000 on 20/1/2019.
 * for project RMS
 * package name mvp
 */
interface BaseView<in Presenter> {
    fun setPresenter(presenter : Presenter)
}