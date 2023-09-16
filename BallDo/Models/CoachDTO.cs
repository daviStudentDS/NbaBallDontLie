using System.ComponentModel.DataAnnotations;

namespace BallDo.Models
{
    public class CoachDTO
    {
        public int Id { get; set; }

        [Required(ErrorMessage = "O nome do treinador é obrigatório.")]
        [StringLength(50, ErrorMessage = "O nome do treinador deve ter no máximo 50 caracteres.")]
        public string Name { get; set; }

        [Range(0, int.MaxValue, ErrorMessage = "Os anos de experiência devem ser um número positivo.")]
        public int ExperienceYears { get; set; }

        // Remova a anotação [Required] da propriedade Team
        public TeamDTO? Team { get; set; }
    }

}
