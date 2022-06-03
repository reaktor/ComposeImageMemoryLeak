package com.example.imagememoryleak

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import com.example.imagememoryleak.ui.theme.ImageMemoryLeakTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imageLoader = ImageLoader.Builder(applicationContext).logger(object : coil.util.Logger {
            override var level: Int = Log.INFO

            override fun log(tag: String, priority: Int, message: String?, throwable: Throwable?) {
                Log.i("ImageLoading", message ?: "")
            }

        }).build()
        setContent {
            CompositionLocalProvider(
                LocalImageLoader provides imageLoader
            ) {
                ImageMemoryLeakTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        LazyGridOfImages()
                    }
                }
            }
        }
    }
}

val knownImages = listOf(
    R.drawable.abhinav_bhardwaj_hqbkyknjrho_unsplash,
    R.drawable.abhishek_tiwari_avpojxuohkg_unsplash,
    R.drawable.aedrian_o0eglc8h0ls_unsplash,
    R.drawable.aedrian_xoylyp20eek_unsplash,
    R.drawable.agnieszka_kowalczyk_knq_pupdxna_unsplash,
    R.drawable.alain_rkpxxcztszi_unsplash,
    R.drawable.albulena_panduri_2ntbw_snsfy_unsplash,
    R.drawable.alek_newton_uze_lonbmhi_unsplash,
    R.drawable.alexander_ant_skh6b9hadyu_unsplash,
    R.drawable.alexander_ant_so1p671yat0_unsplash,
    R.drawable.annika_ashley_pe4o4jrpqj4_unsplash,
    R.drawable.clay_leconey_qqlfi0ldc8e_unsplash,
    R.drawable.dante_labella__jqf3261cce_unsplash,
    R.drawable.eberhard_grossgasteiger_14cswsk3rl8_unsplash,
    R.drawable.eberhard_grossgasteiger_ail3opj8rvo_unsplash,
    R.drawable.eberhard_grossgasteiger_hjylejoxwns_unsplash,
    R.drawable.eberhard_grossgasteiger_ulc4cl2mgk8_unsplash,
    R.drawable.eberhard_grossgasteiger_uqzfrpgkn0g_unsplash,
    R.drawable.emanuel_turbuc_pxombixhzvk_unsplash,
    R.drawable.hans_ott_nljrvdmr2ja_unsplash,
    R.drawable.harold_wainwright_aek8x33l7v4_unsplash,
    R.drawable.jeremy_hynes__uxdy87flzk_unsplash,
    R.drawable.jon_tyson_dr7gtzvpy34_unsplash,
    R.drawable.jon_tyson_jrh935zf4zy_unsplash,
    R.drawable.kellen_riggin_mbbqygvykna_unsplash,
    R.drawable.kiko_camaclang_i8tv3rfwtte_unsplash,
    R.drawable.kym_mackinnon_l6veydsuh7u_unsplash,
    R.drawable.marek_piwnicki_f_ejlubi6aa_unsplash,
    R.drawable.marek_piwnicki_jumej5w3ujo_unsplash,
    R.drawable.marek_piwnicki_m7d3wdzkrk8_unsplash,
    R.drawable.marek_piwnicki_t0ximw8jqeq_unsplash,
    R.drawable.masaru_suzuki_tolhfuh0jao_unsplash,
    R.drawable.mike_hindle_kj9ye97ofe4_unsplash,
    R.drawable.milad_fakurian__bv_z1ynhmi_unsplash,
    R.drawable.mingrui_he_ku1erkq92rk_unsplash,
    R.drawable.oguzhan_edman_kxv3xnpqur4_unsplash,
    R.drawable.pawan_thapa_nhwllg71j4a_unsplash,
    R.drawable.rob_potter_8ex2iiiy_ws_unsplash,
    R.drawable.rob_potter_rdiqwro6myq_unsplash,
    R.drawable.rogov_6boic68nzma_unsplash,
    R.drawable.rogov_bdg_hd0uzgy_unsplash,
    R.drawable.ruan_richard_rodrigues__fexpfw26ki_unsplash,
    R.drawable.stephen_leonardi_xyaqkj8573i_unsplash,
    R.drawable.till_daling_wnej5jxi0_k_unsplash,
    R.drawable.viktor_forgacs_gym0cmouzoa_unsplash,
    R.drawable.xhulio_selenica_mx1vxbdu_ya_unsplash,
    R.drawable.zoltan_dioszegi_lsezuyfkfyq_unsplash,
)

@Composable
fun LazyGridOfImages() {
    val chunkedItems = remember { knownImages.chunked(3) }
    LazyColumn {
        items(items = chunkedItems) { drawableIds ->
            Row {
                for (drawableId in drawableIds) {
                    Box(Modifier.size(300.dp)) {
                        Image(
                            painter = rememberImagePainter(data = drawableId),
                            contentDescription = "$drawableId",
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ImageMemoryLeakTheme {
        LazyGridOfImages()
    }
}