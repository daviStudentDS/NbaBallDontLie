using System.ComponentModel.DataAnnotations;

namespace BallDo.Models
{
    public class TeamDTO
    {
        public int Id { get; set; }

        [Required(ErrorMessage = "O nome do time é obrigatório.")]
        [StringLength(50, ErrorMessage = "O nome do time deve ter no máximo 50 caracteres.")]
        public string Name { get; set; }

        // Indica que Players é uma lista que deve ter pelo menos um elemento.
        [MinLength(1, ErrorMessage = "O time deve ter pelo menos um jogador.")]
        public List<PlayerDTO> Players { get; set; }

        public CoachDTO Coach { get; set; }
    }
}
