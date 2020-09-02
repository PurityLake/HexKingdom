#ifdef GL_ES
precision mediump float;
#endif

in vec2 texcoord;

uniform sampler2D texture;

void main() {
    gl_FragColor = texture2D(texture, texcoord);
}