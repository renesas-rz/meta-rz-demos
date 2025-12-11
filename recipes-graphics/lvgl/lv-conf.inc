PACKAGECONFIG ??= "drm"

PACKAGECONFIG[drm] = ",,libdrm"
PACKAGECONFIG[fbdev] = ",,"
PACKAGECONFIG[gridnav] = ",,"
PACKAGECONFIG[thorvg] = ",,"
PACKAGECONFIG[sdl] = ",,virtual/libsdl2 libsdl2-image"

# Add libdrm include if drm is selected in PACKAGECONFIG
TARGET_CFLAGS += "${@bb.utils.contains('PACKAGECONFIG', 'drm', '-I${STAGING_INCDIR}/libdrm', '', d)}"

LVGL_CONFIG_USE_DRM = "${@bb.utils.contains('PACKAGECONFIG', 'drm', '1', '0', d)}"

LVGL_CONFIG_USE_EVDEV = "${@bb.utils.contains_any('PACKAGECONFIG', 'drm fbdev', '1', '0', d)}"

LVGL_CONFIG_USE_FBDEV = "${@bb.utils.contains('PACKAGECONFIG', 'fbdev', '1', '0', d)}"

LVGL_CONFIG_USE_SDL = "${@bb.utils.contains('PACKAGECONFIG', 'sdl', '1', '0', d)}"

LVGL_CONFIG_SDL_FULLSCREEN ?= "0"

LVGL_CONFIG_LV_MEM_CUSTOM ?= "0"
LVGL_CONFIG_LV_MEM_SIZE ?= "(64 * 1024U)"
LVGL_CONFIG_LV_COLOR_DEPTH ?= "32"

LVGL_CONFIG_LV_USE_LOG    ?= "0"
LVGL_CONFIG_LV_LOG_LEVEL  ?= "LV_LOG_LEVEL_WARN"
LVGL_CONFIG_LV_LOG_PRINTF ?= "0"

LVGL_CONFIG_LV_USE_FONT_COMPRESSED ?= "0"

LVGL_CONFIG_LV_USE_GRIDNAV = "${@bb.utils.contains('PACKAGECONFIG', 'gridnav', '1', '0', d)}"

LVGL_CONFIG_LV_THEME_DEFAULT_DARK ?= "0"

LVGL_CONFIG_USE_LOTTIE = "${@bb.utils.contains('PACKAGECONFIG', 'thorvg', '1', '0', d)}"

LVGL_CONFIG_USE_VECTOR_GRAPHICS = "${@bb.utils.contains('PACKAGECONFIG', 'thorvg', '1', '0', d)}"

LVGL_CONFIG_USE_THORVG_INTERNAL = "${@bb.utils.contains('PACKAGECONFIG', 'thorvg', '1', '0', d)}"

LVGL_CONFIG_USE_MATRIX = "${@bb.utils.contains('PACKAGECONFIG', 'thorvg', '1', '0', d)}"

LVGL_CONFIG_USE_FLOAT = "${@bb.utils.contains('PACKAGECONFIG', 'thorvg', '1', '0', d)}"

LVGL_CONFIG_LV_USE_STDLIB = "LV_STDLIB_CLIB"

LVGL_CONFIG_USE_FS_STDIO = "1"
LVGL_CONFIG_FS_STDIO_LETTER = "'L'"
LVGL_FS_STDIO_CACHE_SIZE = "4096"

LVGL_CONFIG_USE_LODEPNG = "1"
LVGL_CONFIG_USE_LIBPNG = "0"
LVGL_CONFIG_USE_BMP = "1"
LVGL_CONFIG_USE_TJPGD = "1"
LVGL_CONFIG_USE_LIBJPEG_TURBO = "0"
LVGL_CONFIG_USE_GIF = "1"
LVGL_CONFIG_BIN_DECODER_RAM_LOAD = "1"
LVGL_CONFIG_GIF_CACHE_DECODE_DATA = "0"
LVGL_CONFIG_CACHE_DEF_SIZE = "0"
LVGL_CONFIG_IMAGE_HEADER_CACHE_DEF_CNT = "0"

DEBUG_BUILD ??= "0"

ALLOW_EMPTY:${PN} = "1"

EXTRA_OECMAKE += "-Dinstall:BOOL=ON -DLIB_INSTALL_DIR=${baselib}"

do_configure:append() {
    # If there is a configuration template, start from that
    [ -r "${S}/lv_conf_template.h" ] && cp -Lv "${S}/lv_conf_template.h" "${S}/lv_conf.h"

    sed -r -e "s|#if 0 .*Set it to \"1\" to enable content.*|#if 1 // Enabled by ${PN}|" \
        \
        -e "s|^([[:space:]]*#define LV_USE_LINUX_DRM[[:space:]]).*|\1${LVGL_CONFIG_USE_DRM}|" \
        \
        -e "s|^([[:space:]]*#define LV_USE_LINUX_FBDEV[[:space:]]).*|\1${LVGL_CONFIG_USE_FBDEV}|" \
        \
        -e "s|^([[:space:]]*#define LV_USE_SDL[[:space:]]).*|\1${LVGL_CONFIG_USE_SDL}|" \
        -e "s|^([[:space:]]*#define LV_USE_DRAW_SDL[[:space:]]).*|\1${LVGL_CONFIG_USE_SDL}|" \
        -e "s|^([[:space:]]*#define LV_SDL_BUF_COUNT[[:space:]]).*|\1 2|" \
        -e "s|^([[:space:]]*#define LV_SDL_FULLSCREEN[[:space:]]).*|\1${LVGL_CONFIG_SDL_FULLSCREEN}|" \
        \
        -e "s|^([[:space:]]*#define LV_COLOR_DEPTH[[:space:]]).*|\1${LVGL_CONFIG_LV_COLOR_DEPTH}|" \
        -e "s|^([[:space:]]*#define LV_MEM_CUSTOM[[:space:]]).*|\1${LVGL_CONFIG_LV_MEM_CUSTOM}|" \
        -e "s|^([[:space:]]*#define LV_MEM_SIZE[[:space:]]).*|\1${LVGL_CONFIG_LV_MEM_SIZE}|" \
        \
        -e "s|^([[:space:]]*#define LV_TICK_CUSTOM[[:space:]]).*|\1 1|" \
        -e "s|^([[:space:]]*#define LV_TICK_CUSTOM_INCLUDE[[:space:]]).*|\1 <stdint.h>|" \
        -e "s|^([[:space:]]*#define LV_TICK_CUSTOM_SYS_TIME_EXPR[[:space:]]).*|extern uint32_t custom_tick_get(void);\n\1 (custom_tick_get())|" \
        \
        -e "s|^([[:space:]]*#define LV_USE_EVDEV[[:space:]]).*|\1${LVGL_CONFIG_USE_EVDEV}|" \
        \
        -e "s|^([[:space:]]*#define LV_USE_ASSERT_NULL[[:space:]]).*|\1${DEBUG_BUILD}|" \
        -e "s|^([[:space:]]*#define LV_USE_ASSERT_MALLOC[[:space:]]).*|\1${DEBUG_BUILD}|" \
        -e "s|^([[:space:]]*#define LV_USE_ASSERT_STYLE[[:space:]]).*|\1${DEBUG_BUILD}|" \
        -e "s|^([[:space:]]*#define LV_USE_ASSERT_MEM_INTEGRITY[[:space:]]).*|\1${DEBUG_BUILD}|" \
        -e "s|^([[:space:]]*#define LV_USE_ASSERT_OBJ[[:space:]]).*|\1${DEBUG_BUILD}|" \
        \
        -e "s|^([[:space:]]*#define LV_USE_LOG[[:space:]]).*|\1${LVGL_CONFIG_LV_USE_LOG}|" \
        -e "s|^([[:space:]]*#define LV_LOG_LEVEL[[:space:]]).*|\1${LVGL_CONFIG_LV_LOG_LEVEL}|" \
        -e "s|^([[:space:]]*#define LV_LOG_PRINTF[[:space:]]).*|\1${LVGL_CONFIG_LV_LOG_PRINTF}|" \
        \
        -e "s|^([[:space:]]*#define LV_USE_FONT_COMPRESSED[[:space:]]).*|\1${LVGL_CONFIG_LV_USE_FONT_COMPRESSED}|" \
        -e "s|^([[:space:]]*#define LV_USE_GRIDNAV[[:space:]]).*|\1${LVGL_CONFIG_LV_USE_GRIDNAV}|" \
        -e "s|^([[:space:]]*#define LV_THEME_DEFAULT_DARK[[:space:]]).*|\1${LVGL_CONFIG_LV_THEME_DEFAULT_DARK}|" \
        \
        -e "s|^([[:space:]]*#define LV_USE_VECTOR_GRAPHIC[[:space:]]).*|\1${LVGL_CONFIG_USE_VECTOR_GRAPHICS}|" \
        -e "s|^([[:space:]]*#define LV_USE_THORVG_INTERNAL[[:space:]]).*|\1${LVGL_CONFIG_USE_THORVG_INTERNAL}|" \
        -e "s|^([[:space:]]*#define LV_USE_MATRIX[[:space:]]).*|\1${LVGL_CONFIG_USE_MATRIX}|" \
        -e "s|^([[:space:]]*#define LV_USE_FLOAT[[:space:]]).*|\1${LVGL_CONFIG_USE_FLOAT}|" \
        \
        -e "s|^([[:space:]]*#define LV_USE_STDLIB_MALLOC[[:space:]]).*|\1${LVGL_CONFIG_LV_USE_STDLIB}|" \
        -e "s|^([[:space:]]*#define LV_USE_STDLIB_STRING[[:space:]]).*|\1${LVGL_CONFIG_LV_USE_STDLIB}|" \
        -e "s|^([[:space:]]*#define LV_USE_STDLIB_SPRINTF[[:space:]]).*|\1${LVGL_CONFIG_LV_USE_STDLIB}|" \
        \
        -e "s|^([[:space:]]*#define LV_USE_FS_STDIO[[:space:]]).*|\1${LVGL_CONFIG_USE_FS_STDIO}|" \
        -e "s|^([[:space:]]*#define LV_FS_STDIO_LETTER[[:space:]]).*|\1${LVGL_CONFIG_FS_STDIO_LETTER}|" \
        -e "s|^([[:space:]]*#define LV_FS_STDIO_CACHE_SIZE[[:space:]]).*|\1${LVGL_FS_STDIO_CACHE_SIZE}|" \
        \
        -e "s|^([[:space:]]*#define LV_USE_LODEPNG[[:space:]]).*|\1${LVGL_CONFIG_USE_LODEPNG}|" \
        -e "s|^([[:space:]]*#define LV_USE_LIBPNG[[:space:]]).*|\1${LVGL_CONFIG_USE_LIBPNG}|" \
        -e "s|^([[:space:]]*#define LV_USE_BMP[[:space:]]).*|\1${LVGL_CONFIG_USE_BMP}|" \
        -e "s|^([[:space:]]*#define LV_USE_TJPGD[[:space:]]).*|\1${LVGL_CONFIG_USE_TJPGD}|" \
        -e "s|^([[:space:]]*#define LV_USE_LIBJPEG_TURBO[[:space:]]).*|\1${LVGL_CONFIG_USE_LIBJPEG_TURBO}|" \
        -e "s|^([[:space:]]*#define LV_USE_GIF[[:space:]]).*|\1${LVGL_CONFIG_USE_GIF}|" \
        -e "s|^([[:space:]]*#define LV_BIN_DECODER_RAM_LOAD[[:space:]]).*|\1${LVGL_CONFIG_BIN_DECODER_RAM_LOAD}|" \
        -e "s|^([[:space:]]*#define LV_GIF_CACHE_DECODE_DATA[[:space:]]).*|\1${LVGL_CONFIG_GIF_CACHE_DECODE_DATA}|" \
        -e "s|^([[:space:]]*#define LV_CACHE_DEF_SIZE[[:space:]]).*|\1${LVGL_CONFIG_CACHE_DEF_SIZE}|" \
        -e "s|^([[:space:]]*#define LV_IMAGE_HEADER_CACHE_DEF_CNT[[:space:]]).*|\1${LVGL_CONFIG_IMAGE_HEADER_CACHE_DEF_CNT}|" \
        \
        -e "s|^([[:space:]]*#define LV_USE_LOTTIE[[:space:]]).*|\1${LVGL_CONFIG_USE_LOTTIE}|" \
        -i "${S}/lv_conf.h"
}
