package com.sjy.viewentry

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.model.VideoOptionModel
import com.shuyu.gsyvideoplayer.player.IjkPlayerManager
import com.shuyu.gsyvideoplayer.player.PlayerFactory
import com.shuyu.gsyvideoplayer.utils.GSYVideoType
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import tv.danmaku.ijk.media.exo2.Exo2PlayerManager
import tv.danmaku.ijk.media.player.IjkMediaPlayer


class ViewActivity : AppCompatActivity() {
    
    private lateinit var gsyVideoView: StandardGSYVideoPlayer
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        gsyVideoView = findViewById(R.id.gsy_video_view)
        PlayerFactory.setPlayManager(IjkPlayerManager::class.java) //ijk模式
//        PlayerFactory.setPlayManager(Exo2PlayerManager::class.java)

        val  list = ArrayList<VideoOptionModel>() 
        list.add(VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "rtsp_transport", "udp"))
        list.add(VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 0))
        list.add(VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "opensles", 0))
        list.add(VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", IjkMediaPlayer.SDL_FCC_RV32))
        list.add(VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 1))
        list.add(VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 1))
        list.add(VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "video-pictq-size", 3))
        list.add(VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "http-detect-range-support", 0))
        list.add(VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "skip_loop_filter", 0))
        list.add(VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "video_play_type",  0))
        list.add(VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "av_sync_type",  0))
        list.add(VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "switch_audio_db",  1))
        list.add(VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "overlay-format",  "fcc-_es2"))
        list.add(VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "analyzeduration",  1))
        list.add(VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "probesize",  1024 * 2))
        list.add(VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "flush_packets",  1))
        list.add(VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "soundtouch",  1))


        GSYVideoManager.instance().optionModelList = list
        GSYVideoType.setRenderType(GSYVideoType.SUFRACE);
        val  source1 = "rtsp://192.168.10.1:8554/live"
        gsyVideoView.setUp(source1, false, "")
        gsyVideoView.getTitleTextView().setVisibility(View.GONE)
        gsyVideoView.startPlayLogic() 
    }
}