using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace BallDo.Models
{
    public class Player
    {
        [Key]
        public int Id { get; set; }

        [MaxLength(100)]
        [Required]
        public string Name { get; set; }

        [MaxLength(100)]
        [Required]
        public string Position { get; set; }

        [Required]
        public int Age { get; set; }

        public int TeamId { get; set; }
        public Team? Team { get; set; }

        public int GoalsScored { get; set; }
    }
}
